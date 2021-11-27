package eapli.base.colaboratormanagement.application;


import eapli.base.colaboratormanagement.application.viaDTO.ListCollaboratorDTOService;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.Funcao;
import eapli.base.colaboratormanagement.dto.ColaboradorDTO;
import eapli.base.colaboratormanagement.dto.ColaboradorDTOParser;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.colaboratormanagement.repository.FuncaoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.repository.EquipaRepository;
import eapli.framework.application.UseCaseController;

import java.util.Calendar;
import java.util.Set;

@UseCaseController
public class AdicionarColaboradorController {

    private final RegisterCollaboratorService registerService = new RegisterCollaboratorService();
    private final RepositoryFactory factory = PersistenceContext.repositories();

    private final ListCollaboratorDTOService colaboratorService = new ListCollaboratorDTOService();

    public Colaborador registerColaborator(String username, String instiEmail, String numMeca, String firstName, String lastName, String nomeCompleto,
                                           Calendar birthDate, String distrito, String concelho, String contactNumber, Funcao function, ColaboradorDTO selectedResponsavel,
                                           Set<Equipa> selectedTeams){
        Colaborador colaborador = new ColaboradorDTOParser(factory.colaboradores()).valueOf(selectedResponsavel);
        return registerService.registerColaborator(username,instiEmail,numMeca,firstName,lastName,nomeCompleto,birthDate,
                distrito,concelho,contactNumber,function,colaborador,selectedTeams);
    }

    public boolean validateEquipasColaborador(Set<Equipa> listaEquipas){
        return registerService.validateEquipasColaborador(listaEquipas);
    }
    public Iterable<Funcao> listFunctions(){
            FuncaoRepository funcaoRepository = factory.funcoes();
            return funcaoRepository.findAll();
    }

    public Iterable<ColaboradorDTO> listColaboradores(){
        return colaboratorService.allColaborators();
    }

    public Iterable<Equipa> listEquipas(){
        EquipaRepository equipaRepository = factory.equipas();
        return equipaRepository.findAll();
    }

}
