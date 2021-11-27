package eapli.base.teammanagement.application;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.NumeroMecanografico;
import eapli.base.colaboratormanagement.dto.ColaboradorDTO;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.domain.Acronimo;
import eapli.base.teammanagement.domain.CodigoUnico;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.domain.TipoEquipa;
import eapli.base.teammanagement.repository.EquipaRepository;
import eapli.base.teammanagement.repository.TipoEquipaRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.utils.EmailHandler;
import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;


import java.util.*;

@UseCaseController
public class RegistarEquipaController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final EquipaRepository equipaRepository = PersistenceContext.repositories().equipas();
    private final TipoEquipaRepository tipoEquipaRepository = PersistenceContext.repositories().tiposEquipa();
    private final ColaboradorRepository colaboradorRepository = PersistenceContext.repositories().colaboradores();
    private final EmailHandler emailHandler = new EmailHandler();

    public Equipa registarEquipa(final TipoEquipa tipoEquipa,final String acronimo, final String codigoUnico, final String designacao,
                                 final Set<ColaboradorDTO> colaboradoresDTO, final Set<ColaboradorDTO> responsaveisDTO){

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RESPONSAVEL_RH, BaseRoles.POWER_USER);
        Set<Colaborador> colaboradores = new HashSet<>();
        Set<Colaborador> responsaveis = new HashSet<>();

        for(ColaboradorDTO c : colaboradoresDTO){
            colaboradores.add(colaboradorRepository.findColaboradorPorNumeroMecanografico(NumeroMecanografico.valueOf(c.numeroMecanografico)));
        }
        for(ColaboradorDTO c : responsaveisDTO){
            responsaveis.add(colaboradorRepository.findColaboradorPorNumeroMecanografico(NumeroMecanografico.valueOf(c.numeroMecanografico)));
        }

        final Equipa newEquipa = new Equipa(Acronimo.valueOf(acronimo), CodigoUnico.valueOf(codigoUnico), Designation.valueOf(designacao), tipoEquipa,
                colaboradores, responsaveis);
        sendEmail(colaboradoresDTO, designacao);
        sendEmail(responsaveisDTO, designacao);
        return  equipaRepository.save(newEquipa);

    }


    public Iterable<TipoEquipa> tiposEquipa(){
        return tipoEquipaRepository.findAll();
    }


    public Iterable<ColaboradorDTO> colaboradores(TipoEquipa t){
        List<ColaboradorDTO> l = new ArrayList<>();
        for(Colaborador c : colaboradorRepository.findAllResponsaveisColaboradoresSemTipoEquipa(t)){
            l.add(c.toDTO());
        }
        return l;
    }

    private void sendEmail(Set<ColaboradorDTO> set, String designacao){
        for (ColaboradorDTO c : set) {
            emailHandler.sendEmail(c.email, "Register in New Team",
                    String.format("Hello %s,\n\tYou were added to a new Team %s.", c.nome, designacao));
        }
    }
}
