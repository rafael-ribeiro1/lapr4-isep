package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.catalogmanagement.application.AddCatalogController;
import eapli.base.catalogmanagement.application.AssociarCriticidadeCatalogoController;
import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.NumeroMecanografico;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.criticidademanagement.repository.CriticidadeRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.teammanagement.domain.CodigoUnico;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.repository.EquipaRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class CatalogBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogBootstrapper.class);

    private static final String DEFAULT_ICON_PATH = "images/isep.png";

    private final AddCatalogController controller = new AddCatalogController();
    private final AssociarCriticidadeCatalogoController assCritController = new AssociarCriticidadeCatalogoController();
    private final ColaboradorRepository colaboradorRepository = PersistenceContext.repositories().colaboradores();
    private final EquipaRepository equipaRepository = PersistenceContext.repositories().equipas();
    private final CriticidadeRepository criticidadeRepository = PersistenceContext.repositories().criticidades();

    @Override
    public boolean execute() {
        // Colaboradores
        Colaborador resp1 = getColaborador(NumeroMecanografico.valueOf("15"));
        Colaborador resp2 = getColaborador(NumeroMecanografico.valueOf("192"));

        // Icon
        byte[] icon;
        final InputStream inputStream = this.getClass()
                .getClassLoader().getResourceAsStream(DEFAULT_ICON_PATH);
        if (inputStream == null) {
            icon = new byte[] {1,2,3};
        } else {
            try {
                icon = IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                icon = new byte[] {1,2,3};
            }
        }

        // Equipas
        List<Equipa> accessAllowed = new ArrayList<>();
        accessAllowed.add(getEquipa(CodigoUnico.valueOf("DC1")));
        accessAllowed.add(getEquipa(CodigoUnico.valueOf("DRH1")));
        accessAllowed.add(getEquipa(CodigoUnico.valueOf("DF1")));

        // Criticidades
        List<Criticidade> criticidades = new ArrayList<>();
        for (Criticidade c : criticidadeRepository.findAll())
            criticidades.add(c);

        registerCatalog("Cotações e Descontos", "Requisitar ou consultar descontos", "Descontos e cotações relativos a vários serviços disponibilizados a empresas ao consumidor", icon, resp1, accessAllowed, criticidades.get(0));
        registerCatalog("Reportar anomalia em equipamento", "Avarias/similar relativo a equipamentos", "Reportar avarias ou outras anomalias relativas a um equipamento adquirido", icon, resp2, accessAllowed, criticidades.get(2));
        registerCatalog("Reportar anomalia em aplicação", "Erros ou bugs da nossa aplicação", "Reportar qualquer problema que experienciou ao usar a nossa plataforma", icon, resp1, accessAllowed, criticidades.get(3));

        return true;
    }

    private Catalog registerCatalog(final String title, final String briefDesc, final String completeDesc,
                                    final byte[] icon, final Colaborador resp, List<Equipa> accessAllowed, Criticidade criticidade) {
        Catalog catalog = null;
        try {
            Catalog c = controller.addCatalog(title, briefDesc, completeDesc, icon, resp, accessAllowed);
            catalog = assCritController.associarCriticidadeCatalogo(c.toDTO(), criticidade);
            LOGGER.info("Registado catalogo com sucesso: " + title);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            LOGGER.warn("Failed accessing DB (catalog)");
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Error: " + e.getMessage());
        }

        return catalog;
    }

    private Colaborador getColaborador(NumeroMecanografico numeroMecanografico){
        return colaboradorRepository.findColaboradorPorNumeroMecanografico(numeroMecanografico);
    }

    private Equipa getEquipa(CodigoUnico cod) {
        return equipaRepository.findEquipaPorCodigoUnico(cod);
    }

}
