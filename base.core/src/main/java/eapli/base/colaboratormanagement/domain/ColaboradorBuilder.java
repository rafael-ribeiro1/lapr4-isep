package eapli.base.colaboratormanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.time.util.Calendars;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;



public class ColaboradorBuilder implements DomainFactory<Colaborador> {

    private static final Logger LOGGER = LogManager.getLogger(SystemUserBuilder.class);

    private SystemUser systemUser;

    private DataNascimento dataNascimento;

    private NumeroMecanografico numeroMecanografico;

    private NomeCurto shortName;

    private NomeCompleto completeName;

    private LocalResidencia localResidencia;

    private NumeroContacto numeroContacto;

    private EmailInstitucional emailInstitucional;

    private Colaborador responsavelHierarquico;

    private Funcao funcao;

    public ColaboradorBuilder() {

    }

    public ColaboradorBuilder with(final SystemUser user,final NumeroMecanografico numeroMecanografico,final DataNascimento dataNascimento,final NomeCurto nomeCurto,
    final NomeCompleto nomeCompleto, final LocalResidencia localResidencia,final NumeroContacto numeroContacto,final EmailInstitucional emailInstitucional,final Colaborador responsavelHierarquico,final Funcao funcao ){
        withSystemUser(user);
        withNumeroMecanografico(numeroMecanografico);
        withDataNascimento(dataNascimento);
        withNomeCurto(nomeCurto);
        withNomeCompleto(nomeCompleto);
        withLocalResidencia(localResidencia);
        withNumeroContacto(numeroContacto);
        withEmailInstitucional(emailInstitucional);
        withResponsavelHierarquico(responsavelHierarquico);
        withFuncao(funcao);
        return this;
    }


    public ColaboradorBuilder with(final SystemUser user, final String numeroMecanografico, final Calendar dataNascimento, final String firstName , final String lastName,
                                   final String nomeCompleto, final String distrito, final String concelho , final String numeroContacto, final String emailInstitucional, final Colaborador responsavelHierarquico, final Funcao funcao){
        withSystemUser(user);
        withDataNascimento(dataNascimento);
        withNomeCurto(firstName,lastName);
        withNomeCompleto(nomeCompleto);
        withLocalResidencia(distrito, concelho);
        withNumeroContacto(numeroContacto);
        withEmailInstitucional(emailInstitucional);
        withNumeroMecanografico(numeroMecanografico);
        withResponsavelHierarquico(responsavelHierarquico);
        withFuncao(funcao);
        return this;
    }


    public ColaboradorBuilder withEmailInstitucional(final String email){
        this.emailInstitucional= new EmailInstitucional(email);
        return this;
    }

    public  ColaboradorBuilder withEmailInstitucional (final EmailInstitucional email ){
        this.emailInstitucional=email;
        return this;
    }

    public ColaboradorBuilder withSystemUser(final SystemUser user){
        this.systemUser=user;
        return this;
    }

    public ColaboradorBuilder withDataNascimento(final Calendar data){
        this.dataNascimento=new DataNascimento(data);
        return this;
    }

    public ColaboradorBuilder withDataNascimento(final DataNascimento data){
        this.dataNascimento=data;
        return this;
    }

    public ColaboradorBuilder withNomeCurto(final String firstName,final String lastName){
        this.shortName=new NomeCurto(firstName,lastName);
        return this;
    }

    public ColaboradorBuilder withNomeCurto(final NomeCurto shortName){
        this.shortName=shortName;
        return this;
    }

    public ColaboradorBuilder withNomeCompleto(final NomeCompleto nomeCompleto){
        this.completeName=nomeCompleto;
        return this;
    }

    public ColaboradorBuilder withNomeCompleto (final String nomeCompleto){
        this.completeName= new NomeCompleto(nomeCompleto);
        return this;
    }

    public ColaboradorBuilder withLocalResidencia (final String distrito,final String concelho){
        this.localResidencia = new LocalResidencia(distrito,concelho);
        return this;
    }

    public ColaboradorBuilder withLocalResidencia (final LocalResidencia local){
        this.localResidencia = local;
        return this;
    }

    public ColaboradorBuilder withNumeroContacto (final NumeroContacto numeroC){
        this.numeroContacto =  numeroC;
        return this;
    }

    public ColaboradorBuilder withNumeroContacto (final String numeroContacto){
        this.numeroContacto=new NumeroContacto(numeroContacto);
        return this;
    }

    public ColaboradorBuilder withResponsavelHierarquico(final Colaborador responsavelHierarquico){
        this.responsavelHierarquico = responsavelHierarquico;
        return this;
    }

    public ColaboradorBuilder withNumeroMecanografico (final String numeroMecanoGrafico){
        this.numeroMecanografico = new NumeroMecanografico(numeroMecanoGrafico);
        return this;
    }

    public ColaboradorBuilder withNumeroMecanografico (final NumeroMecanografico numeroMecanografico){
        this.numeroMecanografico = numeroMecanografico;
        return this;
    }



  public ColaboradorBuilder withFuncao(final Funcao funcaoSelecionada){
       this.funcao = funcaoSelecionada;
      return this;
   }


    @Override
    public Colaborador build() {
        Colaborador novoColaborador;
        novoColaborador = new Colaborador(emailInstitucional,systemUser,dataNascimento,numeroMecanografico,shortName,completeName,localResidencia,numeroContacto,responsavelHierarquico,funcao);
        return novoColaborador;
    }





}
