package eapli.base.teammanagement.application;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.EmailInstitucional;
import eapli.base.colaboratormanagement.dto.ColaboradorDTO;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.domain.CodigoUnico;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.domain.TipoEquipa;
import eapli.base.teammanagement.repository.EquipaRepository;
import eapli.base.teammanagement.repository.TipoEquipaRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.utils.EmailHandler;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.validations.Preconditions;

import java.util.ArrayList;
import java.util.List;

public class AlterarEquipaController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TipoEquipaRepository tipoEquipaRepository = PersistenceContext.repositories().tiposEquipa();
    private final EquipaRepository equipaRepository = PersistenceContext.repositories().equipas();
    private final ColaboradorRepository colaboradorRepository = PersistenceContext.repositories().colaboradores();
    private final EmailHandler emailHandler = new EmailHandler();

    public Equipa removerColaboradorEquipa(Equipa e, ColaboradorDTO colab){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RESPONSAVEL_RH);
        Preconditions.noneNull(e,colab);
        Colaborador c = colaborador(EmailInstitucional.valueOf(colab.email));
        if(e.removerColaboradorEquipa(c)) {
            emailHandler.sendEmail(c.emailInstitucional().toString(), "Removed from Team",
                    String.format("Hello %s,\n\tYou were removed from Team %s.", c.completeName(), e.designacao()));
            return this.equipaRepository.save(e);
        }else {
            return null;
        }
    }

    public Equipa adicionarColaboradorEquipa(Equipa e, ColaboradorDTO colab){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RESPONSAVEL_RH);
        Preconditions.noneNull(e,colab);
        Colaborador c = colaborador(EmailInstitucional.valueOf(colab.email));
        if (e.adicionarColaboradorEquipa(c)) {
            emailHandler.sendEmail(c.emailInstitucional().toString(), "Added to Team",
                    String.format("Hello %s,\n\tYou were added to Team %s.", c.completeName(), e.designacao()));
            return this.equipaRepository.save(e);
        }else{
            return null;
        }
    }

    public Iterable<TipoEquipa> tiposEquipa(){
        return tipoEquipaRepository.findAll();
    }

    public Iterable<Equipa> equipas(TipoEquipa t){
        return equipaRepository.findAllEquipasPorTipoEquipa(t);
    }

    public Iterable<ColaboradorDTO> colaboradoresEquipa(Equipa e){
        List<ColaboradorDTO> l = new ArrayList<>();
        for (Colaborador c : colaboradorRepository.findAllColaboradoresPorEquipa(e))
            l.add(c.toDTO());
        return l;
    }

    public Iterable<ColaboradorDTO> colaboradoresSemTipoEquipa(TipoEquipa t){
        List<ColaboradorDTO> l = new ArrayList<>();
        for (Colaborador c : colaboradorRepository.findAllColaboradoresSemTipoEquipa(t))
            l.add(c.toDTO());
        return l;
    }

    public Equipa equipaPorCodigoUnico(CodigoUnico codigoUnico) {
        return equipaRepository.findEquipaPorCodigoUnico(codigoUnico);
    }

    private Colaborador colaborador(EmailInstitucional email){
        return colaboradorRepository.findColaboradorPorEmail(email);
    }
}
