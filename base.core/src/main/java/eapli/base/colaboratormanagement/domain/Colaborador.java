package eapli.base.colaboratormanagement.domain;

import eapli.base.colaboratormanagement.dto.ColaboradorDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import javax.persistence.*;
import java.util.Objects;


@Entity
public class Colaborador implements AggregateRoot<Long>, DTOable<ColaboradorDTO> {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    @Column(unique = true)
    private NumeroMecanografico mecanographicNumber;

    @Embedded
    @Column(unique = true)
    private EmailInstitucional emailInstitucional;

    @OneToOne(
            orphanRemoval = true,
            cascade = CascadeType.PERSIST)
    private SystemUser systemUser;

    @Embedded
    private DataNascimento dataNascimento;

    @Embedded
    private NomeCurto shortName;

    @Embedded
    private NomeCompleto completeName;

    @Embedded
    private LocalResidencia localResidencia;

    @Embedded
    @Column(unique = true)
    private NumeroContacto numeroContacto;

    @Embedded()
    private Classificacao classificacao;

    @ManyToOne
    private Colaborador responsavelHierarquico;

    @ManyToOne
    private Funcao funcao;


    public Colaborador(EmailInstitucional email, SystemUser sysUser, DataNascimento data ,NumeroMecanografico mecaNumber,
                       NomeCurto shortName , NomeCompleto completeName,LocalResidencia localResidencia,
                       NumeroContacto numeroContacto,Colaborador responsavelHierarquico,Funcao funcao){
        Preconditions.noneNull(email,sysUser,data,mecaNumber,shortName,completeName,localResidencia,
                numeroContacto);

        this.emailInstitucional = email;
        this.systemUser=sysUser;
        this.dataNascimento=data;
        this.mecanographicNumber=mecaNumber;
        this.shortName=shortName;
        this.completeName=completeName;
        this.localResidencia=localResidencia;
        this.numeroContacto=numeroContacto;
        this.funcao=funcao;
        this.responsavelHierarquico=responsavelHierarquico;
        this.classificacao=new Classificacao(0);
    }

    public Colaborador(Colaborador clone){
        Preconditions.nonNull(clone,"O colaborador clone nao pode ser nulo");
        this.emailInstitucional = new EmailInstitucional(clone.emailInstitucional.email());
        this.systemUser=clone.systemUser;
        this.dataNascimento=clone.dataNascimento;
        this.mecanographicNumber=new NumeroMecanografico(clone.mecanographicNumber.numeroMecanografico());
        this.shortName=new NomeCurto(clone.shortName.primeiroNome(),clone.shortName.ultimoNome());
        this.completeName=new NomeCompleto(clone.completeName.toString());
        this.localResidencia= new LocalResidencia(clone.localResidencia.distrito(),clone.localResidencia.concelho()) ;
        this.numeroContacto=new NumeroContacto(clone.numeroContacto.toString());
        this.funcao=clone.funcao;
        this.responsavelHierarquico=clone.responsavelHierarquico;
        this.classificacao=new Classificacao(clone.classificacao.valorClassificacao());
    }

    protected Colaborador(){

    }

    public NumeroMecanografico mecanographicNumber() {
        return mecanographicNumber;
    }

    public EmailInstitucional emailInstitucional() {
        return emailInstitucional;
    }

    public NomeCompleto completeName() {
        return completeName;
    }

    public Colaborador responsavelHierarquico() {
        return responsavelHierarquico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Colaborador)) return false;
        Colaborador that = (Colaborador) o;
        return Objects.equals(id, that.id) || mecanographicNumber.equals(that.mecanographicNumber) || emailInstitucional.equals(that.emailInstitucional);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.id;
    }

    public SystemUser SystemUser() {
        return systemUser;
    }


    public NomeCurto NomeCurto() {
        return shortName;
    }

    public NomeCompleto NomeCompleto() {
        return completeName;
    }

    public LocalResidencia LocalResidencia() {
        return localResidencia;
    }

    public NumeroContacto NumeroContacto() {
        return numeroContacto;
    }

    public DataNascimento DataNascimento() {
        return dataNascimento;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s",shortName,emailInstitucional);
    }

    /**
     * Returns a representation of the content of the object as a DTO.
     *
     * @return a representation of the content of the object as a DTO
     */
    @Override
    public ColaboradorDTO toDTO() {
        return new ColaboradorDTO(this.emailInstitucional.email(),this.mecanographicNumber.numeroMecanografico(),this.shortName.toString());
    }
}
