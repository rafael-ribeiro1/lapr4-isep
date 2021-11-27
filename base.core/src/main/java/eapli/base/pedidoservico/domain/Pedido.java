package eapli.base.pedidoservico.domain;

import eapli.base.activity.domain.*;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.pedidoservico.dto.PedidoDTO;
import eapli.base.servicemanagement.domain.FluxoAtividadesServico;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.form.Attribute;
import eapli.base.servicemanagement.dto.ServiceDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Entity
public class Pedido implements AggregateRoot<String> , DTOable<PedidoDTO> {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq")
    @GenericGenerator(name = "pedido_seq", strategy = "eapli.base.pedidoservico.domain.PedidoIdGenerator",
            parameters = {@Parameter(name = PedidoIdGenerator.INCREMENT_PARAM, value = "50")})
    private String id;

    @ManyToOne
    private Colaborador colab;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataSolicitacao;

    @ManyToOne
    private Service service;
    @Enumerated(EnumType.ORDINAL)
    private Urgencia urgencia;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataLimite;

    @ElementCollection
    private final List<FicheiroPedido> files = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @OneToOne(cascade = CascadeType.ALL)
    private FormResponses formResponses;

    @OneToOne(cascade = CascadeType.ALL)
    private FluxoAtividadesPedido fluxoAtividades;

    protected Pedido() {}

    public Pedido(Colaborador colab, Calendar dataSolicitacao, Service service, Urgencia urgencia, Calendar limiteAprovacao,
                  Calendar dataLimite, List<byte[]> files, Map<Attribute,String> formResponses) {
        Preconditions.noneNull(colab, dataSolicitacao, service, urgencia, dataLimite, files, formResponses);

        this.colab = colab;
        this.dataSolicitacao = dataSolicitacao;
        this.service = service;
        this.urgencia = urgencia;
        this.dataLimite = dataLimite;
        for (byte[] file : files) {
            FicheiroPedido f = new FicheiroPedido(file);
            this.files.add(f);
        }
        this.estado = EstadoPedido.SUBMETIDO;
        this.formResponses = new FormResponses(formResponses);
        this.fluxoAtividades = gerarFluxoAtividades(service, colab, limiteAprovacao, dataLimite);
    }

    public FluxoAtividadesPedido fluxoAtividades() {
        return fluxoAtividades;
    }

    public Service service() {
        return service;
    }

    public Urgencia urgencia() {
        return urgencia;
    }

    public EstadoPedido estadoAtualPedido() {
        return estado;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public String toString() {
        return String.format("%s of service %s", id, service.identity().toString());
    }

    public String details() {
        return "=== Request ================\n"
                + "Identifier = '" + id + "'" +
                ", \n" + "Request date = '" + formatDatetime(dataSolicitacao) + "'" +
                ", \n" + "Service = '" + service.subject() + "'" +
                ", \n" + "Urgency = '" + urgencia.toString() + "'" +
                ", \n" + "Limit date = '" + formatDatetime(dataLimite) + "'" +
                ", \n" + "Number of attached files = '" + files.size() + "'\n" +
                "============================\n";
    }

    private static String formatDatetime(Calendar datetime) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return format.format(datetime.getTime());
    }

    public Colaborador colaboradorOfRequest(){
        return this.colab;
    }

    private FluxoAtividadesPedido gerarFluxoAtividades(Service service, Colaborador colab, Calendar limiteAprovacao, Calendar dataLimite) {
        FluxoAtividadesServico activityFlow = service.activityFlow();
        AtividadeAprovacao aprovacao = null;
        AtividadeAprovacao sAprovacao = activityFlow.atividadeAprovacao();
        if (sAprovacao != null && limiteAprovacao != null) {
            Colaborador resp = null;
            if (sAprovacao.tipoResponsavel() == TipoResponsavel.RESPONSAVEL_HIERARQUICO)
                resp = colab.responsavelHierarquico();
            if (resp == null)
                resp = service.catalog().responsavel();
            aprovacao = sAprovacao.cloneForRequest(limiteAprovacao, resp);
        }
        AtividadeRealizacao realizacao = activityFlow.atividadeRealizacao().cloneForRequest(dataLimite);
        return new FluxoAtividadesPedido(aprovacao, activityFlow.getTipoAtividadeRealizacao(), realizacao);
    }

    public boolean isAtividadeProntaExecutar(TipoAtividadeManual tipoAtividade) {
        if (tipoAtividade == TipoAtividadeManual.APROVACAO)
            return fluxoAtividades.atividadeAprovacao().estado() == EstadoAtividade.PRONTO_EXECUTAR;
        else if (tipoAtividade == TipoAtividadeManual.REALIZACAO)
            return fluxoAtividades.atividadeRealizacao().estado() == EstadoAtividade.PRONTO_EXECUTAR;
        else
            return false;
    }

    public boolean atualizarPedidoAtividadePendente(Colaborador utilizador) {
        return this.fluxoAtividades.atualizarFluxoAtividadePendente(utilizador);
    }

    public void atualizarEstadoPedido(EstadoPedido e){
        this.estado=e;
    }

    public boolean isPedidoConcluido(){
        return this.estado.compareTo (EstadoPedido.CONCLUIDO)==0;
    }

    public static Iterable<Urgencia> urgencias() {
        List<Urgencia> urgencias = new ArrayList<>();
        urgencias.add(Urgencia.REDUZIDO);
        urgencias.add(Urgencia.MODERADO);
        urgencias.add(Urgencia.URGENTE);
        return urgencias;
    }

    public void saveResponsesOfActivity(TipoAtividadeManual tipoAtividade, Map<Attribute, String> formResponses) {
        fluxoAtividades.saveResponsesOfActivity(tipoAtividade, formResponses);
    }

    public boolean verifyAccessColaborator(Colaborador colaborador){
        return this.service.catalog().verifyAccessColaborator(colaborador);
    }

    @Override
    public PedidoDTO toDTO() {
        String id=this.id;
        String requestDate=calendarToString ( dataSolicitacao );
        String deadline=calendarToString ( dataLimite );
        ServiceDTO service=this.service.toDTO ();
        String urgency=this.urgencia.toString ();
        String state=this.estado.toString ();
        return new PedidoDTO (id,requestDate,deadline,service,urgency,state );
    }

    private String calendarToString(Calendar date){
        StringBuilder b=new StringBuilder ();
        int year=date.get ( Calendar.YEAR );
        int month=date.get ( Calendar.MONTH )+1;
        int day=date.get ( Calendar.DATE );
        b.append ( day ).append ( "-" ).append(month).append ( "-" ).append ( year );
        return b.toString ();
    }
}
