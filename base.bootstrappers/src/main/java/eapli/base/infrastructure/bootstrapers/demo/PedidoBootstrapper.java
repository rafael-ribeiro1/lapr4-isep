package eapli.base.infrastructure.bootstrapers.demo;


import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.NumeroMecanografico;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.pedidoservico.application.SolicitarServicoController;
import eapli.base.pedidoservico.domain.Urgencia;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.ServiceCode;
import eapli.base.servicemanagement.domain.form.Attribute;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.*;

public class PedidoBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(PedidoBootstrapper.class);
    private final RepositoryFactory factory = PersistenceContext.repositories();
    private final SolicitarServicoController controller = new SolicitarServicoController();

    @Override
    public boolean execute() {

        Calendar old = Calendar.getInstance();


        Calendar now = Calendar.getInstance();

        Calendar after = Calendar.getInstance();

        Calendar endsIn1Hour = Calendar.getInstance();
        endsIn1Hour.add(Calendar.MINUTE,45);


        Service s1 = factory.services().ofIdentity(new ServiceCode("VendasServicos1")).orElse(null);

        Service s2 = factory.services().ofIdentity(new ServiceCode("VendasServicos2")).orElse(null);

        Service s3 = factory.services().ofIdentity(new ServiceCode("AnomaliaEquipamento1")).orElse(null);

        Service s4 = factory.services().ofIdentity(new ServiceCode("AnomaliaComunicacao1")).orElse(null);

        Service s5 = factory.services().ofIdentity(new ServiceCode("AnomaliaAplicacao1")).orElse(null);

        Service s6 = factory.services().ofIdentity(new ServiceCode("AnomaliaAplicacao2")).orElse(null);

        Service s7 = factory.services().ofIdentity(new ServiceCode("AusenciaPreco")).orElse(null);

        Service s8 = factory.services().ofIdentity(new ServiceCode("PedirPreco")).orElse(null);


        Map<Attribute,String> formResponses = new HashMap<>();

        for (int i = 0; i < 20; i++) {
            createAutomaticTasks ( s1,now,after,formResponses,String.format("attribute%d",i) );
        }

        now.add(Calendar.MINUTE, 45);

        formResponses.clear();
        createManualTasks(s2,now, endsIn1Hour,formResponses,"attribute0", Urgencia.REDUZIDO);

        formResponses.clear();
        now.add(Calendar.MINUTE, 45);
        endsIn1Hour.add(Calendar.MINUTE,3);
        createManualTasks(s3,now, endsIn1Hour,formResponses,"attribute1", Urgencia.MODERADO);

        endsIn1Hour = Calendar.getInstance();
        endsIn1Hour.add(Calendar.MINUTE,45);
        formResponses.clear();
        createManualTasks(s4,endsIn1Hour,endsIn1Hour,formResponses, "attribute2", Urgencia.URGENTE);

        after.add(Calendar.HOUR_OF_DAY, 3);
        after.add(Calendar.YEAR, 3);

        formResponses.clear();
        createManualTasks(s5,old,old,formResponses,"attribute3", Urgencia.REDUZIDO);
        now.add(Calendar.MONTH, 2);
        after.add(Calendar.MONTH, 9);


        formResponses.clear();
        now.add(Calendar.MONTH, 2);
        after.add(Calendar.MONTH, 9);
        createManualTasks(s1,now,after,formResponses,"attribute4",Urgencia.MODERADO);

        formResponses.clear();
        createManualTasks(s2, after, after, formResponses, "attribute5", Urgencia.URGENTE);

        formResponses.clear();
        after.add(Calendar.HOUR_OF_DAY, 3);
        after.add(Calendar.YEAR, 3);
        createManualTasks(s3,now,after,formResponses, "attribute26", Urgencia.REDUZIDO);


        formResponses.clear();
        after.add(Calendar.HOUR_OF_DAY, 3);
        after.add(Calendar.YEAR, 2);
        createManualTasks(s5, endsIn1Hour, after, formResponses, "attribute27", Urgencia.MODERADO);

        formResponses.clear();
        now.add(Calendar.MINUTE, 45);
        endsIn1Hour.add(Calendar.MINUTE,3);
        after.add(Calendar.YEAR, 5);
        createManualTasks(s4, Calendar.getInstance(), Calendar.getInstance(), formResponses, "attribute28",  Urgencia.URGENTE);

        endsIn1Hour = Calendar.getInstance();
        endsIn1Hour.add(Calendar.MINUTE,45);

        formResponses.clear();
        now.add(Calendar.MINUTE, 45);
        endsIn1Hour.add(Calendar.MINUTE,3);
        after.add(Calendar.YEAR, 5);
        createManualTasks(s6,endsIn1Hour,endsIn1Hour,formResponses,"attribute29",Urgencia.REDUZIDO);

        formResponses.clear();
        now.add(Calendar.MINUTE, 45);
        endsIn1Hour.add(Calendar.MINUTE,3);
        after.add(Calendar.YEAR, 5);
        createManualTasks(s6,after,after,formResponses,"attribute30",Urgencia.MODERADO);

        formResponses.clear();
        now.add(Calendar.MINUTE, 45);
        endsIn1Hour.add(Calendar.MINUTE,3);
        after.add(Calendar.YEAR, 5);
        createManualTasks(s3,after,after,formResponses,"attribute31", Urgencia.URGENTE);


        formResponses.clear();
        now.add(Calendar.MINUTE, 45);
        endsIn1Hour.add(Calendar.MINUTE,3);
        after.add(Calendar.YEAR, 5);
        createManualTasks(s5,endsIn1Hour,endsIn1Hour,formResponses,"attribute32",Urgencia.REDUZIDO);


        formResponses.clear();
        now.add(Calendar.MINUTE, 45);
        endsIn1Hour.add(Calendar.MINUTE,3);
        after.add(Calendar.YEAR, 5);
        createManualTasks(s7,now,after,formResponses,"attribute29",Urgencia.REDUZIDO);

        formResponses.clear();
        now.add(Calendar.MINUTE, 45);
        endsIn1Hour.add(Calendar.MINUTE,3);
        after.add(Calendar.YEAR, 5);
        createManualTasks(s8,now,after,formResponses,"attribute30",Urgencia.MODERADO);

        formResponses.clear();
        now.add(Calendar.MINUTE, 45);
        endsIn1Hour.add(Calendar.MINUTE,3);
        after.add(Calendar.YEAR, 5);
        createManualTasks(s7,endsIn1Hour,endsIn1Hour,formResponses,"attribute31", Urgencia.URGENTE);


        formResponses.clear();
        now.add(Calendar.MINUTE, 45);
        endsIn1Hour.add(Calendar.MINUTE,3);
        after.add(Calendar.YEAR, 5);
        createManualTasks(s8,now,after,formResponses,"attribute32",Urgencia.REDUZIDO);

        return true;
    }

    private void createAutomaticTasks(Service s, Calendar now, Calendar after, Map<Attribute,String> formResponses, String attribute){
        Attribute t = s.serviceForm().attributes().get(0);
        formResponses.put(t, attribute);
        register(factory.colaboradores().findColaboradorPorNumeroMecanografico(NumeroMecanografico.valueOf("192")),s,
                Urgencia.REDUZIDO, now, after, new ArrayList<>(), formResponses); //automatica
    }

    private void createManualTasks(Service s, Calendar now, Calendar after, Map<Attribute,String> formResponses, String attribute, Urgencia urgencia){
        Attribute t = s.serviceForm().attributes().get(0);
        formResponses.put(t,attribute);
        register(factory.colaboradores().findColaboradorPorNumeroMecanografico(NumeroMecanografico.valueOf("192")),s,
                urgencia, now, after, new ArrayList<>(), formResponses);
    }

    private void register(Colaborador c, Service service, Urgencia urgencia, Calendar limiteAprovacao, Calendar dataLimite,
                          List<byte[]> files, Map<Attribute,String> formResponses) {
        try {
           controller.solicitarServico(c, service, urgencia, limiteAprovacao, dataLimite, files, formResponses);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.trace("Assuming existing record", e);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while alerting the engine server");
        }
    }
}
