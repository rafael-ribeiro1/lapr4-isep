package eapli.base.activity.application;


import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.criticidademanagement.repository.CriticidadeRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.pedidoservico.domain.Urgencia;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.pedidoservico.repository.PedidoRepository;
import eapli.framework.application.ApplicationService;

@ApplicationService
public class HTMLRequestActivityPendingService {


    private final RepositoryFactory factory = PersistenceContext.repositories();
    private final PedidoRepository repository = factory.pedidos();



    public String getResponse(Colaborador colaborador){

        StringBuilder stringBuilder = new StringBuilder();
        int nAtividadesPendentes = repository.numberPendingActivities(colaborador);
        int nAtividadesLate = repository.numberLatePendingActivities(colaborador);
        int nAtividades1Hour = repository.oneHourAwayPendingActitivities(colaborador);
        int atividadesUrgReduzida = repository.urgencyPendingActivities(colaborador, Urgencia.REDUZIDO,true).size();
        int atividadesUrgModerada = repository.urgencyPendingActivities(colaborador, Urgencia.MODERADO,true).size();
        int atividadesUrgUrgente = repository.urgencyPendingActivities(colaborador, Urgencia.URGENTE,true).size();

        stringBuilder.append(nAtividadesPendentes);
        stringBuilder.append(";");
        stringBuilder.append(nAtividadesLate);
        stringBuilder.append(";");
        stringBuilder.append(nAtividades1Hour);
        stringBuilder.append(";");
        stringBuilder.append(atividadesUrgReduzida);
        stringBuilder.append(";");
        stringBuilder.append(atividadesUrgModerada);
        stringBuilder.append(";");
        stringBuilder.append(atividadesUrgUrgente);
        stringBuilder.append(";");

        final CriticidadeRepository criticidadeRepository = factory.criticidades();

        Iterable<Criticidade> criticidades = criticidadeRepository.findAll();


         for(Criticidade criticidade : criticidades){
            int nOcorrencias = repository.PendingActivitiesByCriticality(colaborador,criticidade,true).size();
            stringBuilder.append(nOcorrencias);
            stringBuilder.append(",");
            stringBuilder.append(criticidade.identity());
            stringBuilder.append(";");
         }


        return stringBuilder.toString();
    }

}
