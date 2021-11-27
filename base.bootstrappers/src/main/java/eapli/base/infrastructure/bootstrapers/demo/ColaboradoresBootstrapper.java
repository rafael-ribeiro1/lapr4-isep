package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.colaboratormanagement.application.AdicionarColaboradorController;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.EmailInstitucional;
import eapli.base.colaboratormanagement.dto.ColaboradorDTO;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class ColaboradoresBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ColaboradoresBootstrapper.class);
    private final AdicionarColaboradorController controller = new AdicionarColaboradorController();

    private final RepositoryFactory factory = PersistenceContext.repositories();

    private final ColaboradorRepository colaboradorRepository = factory.colaboradores();

    @Override
    public boolean execute() {


        Calendar calendar = new GregorianCalendar();
        calendar.set(2001,11,20);

        register("anaaraujo","anaaraujo@email.com","121", "Ana", "Araujo", "Ana Lia Araujo", calendar, "Bragança", "Macedo de Cavaleiros", "911876543", null );

        ColaboradorDTO responsavel = colaboradorRepository.findColaboradorPorEmail(new EmailInstitucional("anaaraujo@email.com")).toDTO();
        calendar.set(2000,02,11);
        register("eduardoCouto","1190549@isep.ipp.pt","192","Eduardo","Couto","Eduardo Ribeiro Couto",calendar,"Porto","Penafiel","910720111",responsavel);
        calendar.set(1992,01,05);
        register("petra","1190975@isep.ipp.pt","13","Petra","Fumo","Petra Meio Fumo",calendar,"Guarda","Trofa","915720543",responsavel);
        responsavel = colaboradorRepository.findColaboradorPorEmail(new EmailInstitucional("1190975@isep.ipp.pt")).toDTO();
        register("rafaelmoreira","1181055@isep.ipp.pt","14", "Rafael", "Moreira", "Rafael Moreira", calendar, "Porto", "Vila Nova de Gaia", "912345678", responsavel );
        calendar.set(1995,06,11);
        register("rafaelribeiro","1190977@isep.ipp.pt","15", "Rafael", "Ribeiro", "Rafael António Ribeiro", calendar, "Porto", "Porto", "919876543", responsavel );
        responsavel = colaboradorRepository.findColaboradorPorEmail(new EmailInstitucional("1181055@isep.ipp.pt")).toDTO();
        calendar.set(1982,12,01);
        register("mariajoao","joaomaria@email.com","150", "Maria", "Meireles", "Maria João Meireles", calendar, "Vila Real", "Vila Real", "917676543", responsavel );

        return true;
    }

    private void register(String username, String instiEmail, String numMeca, String firstName, String lastName, String nomeCompleto,
                          Calendar birthDate, String distrito, String concelho, String contactNumber, ColaboradorDTO selectedResponsavel){

        try{
            controller.registerColaborator(username, instiEmail, numMeca, firstName, lastName, nomeCompleto, birthDate, distrito, concelho, contactNumber, null, selectedResponsavel, new HashSet<>());
            LOGGER.info("Registado colaborador com username: "+username);
        }catch (final IntegrityViolationException | ConcurrencyException e){
            LOGGER.warn("ERRO AO REGISTAR COLABORADOR" + username);
        }

    }

}
