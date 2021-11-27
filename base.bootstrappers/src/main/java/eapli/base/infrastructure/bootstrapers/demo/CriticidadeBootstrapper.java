package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.criticidademanagement.application.DefinirCriticidadeController;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

public class CriticidadeBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(CriticidadeBootstrapper.class);

    private DefinirCriticidadeController controller = new DefinirCriticidadeController();

    @Override
    public boolean execute() {
        register("Calmo",3,new Color(0,0,255),120,80,80,60);
        register("Ligeiramente Medio",6,new Color(0,125,255),110,70,60,45);
        register("Medio",10,new Color(255,255,0),80,50,50,40);
        register("Ligeiramente Grave",14,new Color(255,150,0),60,40,40,30);
        register("Grave",16,new Color(255,75,0),50,35,30,25);
        register(" Muito Grave",16,new Color(255,0,0),30,20,25,15);
        return false;
    }

    private void register(final String etiqueta , final int valorCriticidade, final Color cor,final int temMaxApro,final int temAvgAprova,
                          final int tempMaxReso,final int tempAvgReso){

        try {
            controller.registarCriticidade(etiqueta,valorCriticidade,cor,temMaxApro,temAvgAprova,tempMaxReso,tempAvgReso);
            LOGGER.info("Registado criticidade com etiqueta: " +etiqueta);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", etiqueta);
            LOGGER.trace("Assuming existing record", e);
        }

    }


}
