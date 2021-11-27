package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.colaboratormanagement.domain.NumeroMecanografico;
import eapli.base.colaboratormanagement.dto.ColaboradorDTO;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.application.RegistarEquipaController;
import eapli.base.teammanagement.domain.CodigoUnico;
import eapli.base.teammanagement.domain.TipoEquipa;
import eapli.base.teammanagement.repository.TipoEquipaRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EquipaBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(EquipaBootstrapper.class);

    private final RegistarEquipaController controller = new RegistarEquipaController();
    private final TipoEquipaRepository tipoEquipaRepository = PersistenceContext.repositories().tiposEquipa();
    private final ColaboradorRepository colaboradorRepository = PersistenceContext.repositories().colaboradores();

    @Override
    public boolean execute() {
        Set<ColaboradorDTO> responsaveisTeam1 = new HashSet<>();
        responsaveisTeam1.add(getColaborador(NumeroMecanografico.valueOf("192")));

        Set<ColaboradorDTO> colaboradoresTeam1 = new HashSet<>();
        colaboradoresTeam1.add(getColaborador(NumeroMecanografico.valueOf("13")));

        Set<ColaboradorDTO> responsaveisTeam2 = new HashSet<>();
        responsaveisTeam2.add(getColaborador(NumeroMecanografico.valueOf("14")));

        Set<ColaboradorDTO> colaboradoresTeam2 = new HashSet<>();
        colaboradoresTeam2.add(getColaborador(NumeroMecanografico.valueOf("15")));

        Set<ColaboradorDTO> responsaveisTeam3 = new HashSet<>();
        responsaveisTeam3.add(getColaborador(NumeroMecanografico.valueOf("150")));

        Set<ColaboradorDTO> colaboradoresTeam3 = new HashSet<>();
        colaboradoresTeam3.add(getColaborador(NumeroMecanografico.valueOf("121")));

        register(getTipoEquipa(CodigoUnico.valueOf("DCOM1")), "DCA", "DC1", "DCOM-1",
                colaboradoresTeam1, responsaveisTeam1);
        register(getTipoEquipa(CodigoUnico.valueOf("DRH1")),"DRHA", "DRH1", "DRH-1",
                colaboradoresTeam2,responsaveisTeam2);
        register(getTipoEquipa(CodigoUnico.valueOf("DFIN1")),"DFA", "DF1", "DFIN-1",
                colaboradoresTeam3,responsaveisTeam3);
        return true;
    }

    private void register(TipoEquipa tipoEquipa, String acronimo, String codigoUnico, String designacao,
                          Set<ColaboradorDTO> colaboradores, Set<ColaboradorDTO> responsaveis) {
        try {
            controller.registarEquipa(tipoEquipa, acronimo, codigoUnico, designacao, colaboradores, responsaveis);
            LOGGER.info(codigoUnico);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", codigoUnico);
            LOGGER.trace("Assuming existing record", e);
        }
    }

    private TipoEquipa getTipoEquipa(CodigoUnico codigoUnico){
        Optional<TipoEquipa> opt = tipoEquipaRepository.findTipoEquipaPorCodigoUnico(codigoUnico);
        return opt.orElse(null);
    }

    private ColaboradorDTO getColaborador(NumeroMecanografico numeroMecanografico){
        return colaboradorRepository.findColaboradorPorNumeroMecanografico(numeroMecanografico).toDTO();
    }
}
