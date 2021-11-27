package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.repository.CatalogRepository;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.EmailInstitucional;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.criticidademanagement.repository.CriticidadeRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.servicemanagement.domain.ServiceDraft;
import eapli.base.servicemanagement.domain.ServiceDraftBuilder;
import eapli.base.servicemanagement.domain.form.AttributeDTO;
import eapli.base.teammanagement.domain.CodigoUnico;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.repository.EquipaRepository;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ServiceDraftBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger( ServiceDraftBootstrapper.class);

    private static final String DEFAULT_ICON_PATH = "images/isep.png";

    private final CatalogRepository catalogRepository= PersistenceContext.repositories().catalogs ();
    private final EquipaRepository equipaRepository = PersistenceContext.repositories().equipas();

    private final CriticidadeRepository criticidadeRepository = PersistenceContext.repositories().criticidades();

    @Override
    public boolean execute() {
        Catalog catalog;
        Iterator<Catalog> catalogs= getCatalogs ();
        catalog= getCatalog (catalogs);
        byte[] icon= new byte[] {1,2,3};

        Colaborador colab = PersistenceContext.repositories ().colaboradores ().findColaboradorPorEmail ( new EmailInstitucional ( "1190549@isep.ipp.pt" ) );

        List<AttributeDTO> attributeDTOList = new ArrayList<>();
        // String name, String description, String label, String dataType, String regex
        AttributeDTO attributeDTO = new AttributeDTO("Atributo","Descricao","Label a" , "TEXT","[a-zA-Z]*");
        attributeDTOList.add(attributeDTO);

        List<Criticidade> listCriticidade = this.getCriticidades();

        ServiceDraft draft1=new ServiceDraftBuilder ()
                .withServiceId ( "TesteConversaoDraftEmServico1" )
                .withTitle ( "Teste" )
                .withBriefDescription ( "Breve" )
                .withCompleteDescription ( "Completa" )
                .withKeyword ( "keywordTeste" )
                .withFilloutFormName ( "fillout" )
                .withFilloutFormAttribute ( "at1Teste","desc","labelTeste","TEXT","." )
                .withCatalog ( catalog )
                .withManualRealizationActivity("Formulario1", attributeDTOList,colab)
                .withCriticality (listCriticidade.get(3))
                .withIcon ( icon )
                .build ();

        ServiceDraft draft2=new ServiceDraftBuilder ()
                .withServiceId ( "TesteConversaoDraftEmServico2" )
                .withTitle ( "Teste" )
                .withBriefDescription ( "Breve" )
                .withCompleteDescription ( "Completa" )
                .withKeyword ( "keywordTeste" )
                .withFilloutFormName ( "fillout" )
                .withFilloutFormAttribute ( "at1Teste","desc","labelTeste","TEXT","." )
                .withCatalog ( catalog )
                .withManualRealizationActivity("Formulario2", attributeDTOList,colab)
                .withCriticality (listCriticidade.get(3))
                .withIcon ( icon )
                .build ();

        icon= new byte[] {4,5,6};
        catalog = getCatalog ( catalogs );

        ServiceDraft draft3=new ServiceDraftBuilder ()
                .withServiceId ( "TesteConversaoDraftEmServico3" )
                .withTitle ( "Teste" )
                .withBriefDescription ( "Breve" )
                .withCompleteDescription ( "Completa" )
                .withKeyword ( "keywordTeste" )
                .withFilloutFormName ( "fillout" )
                .withFilloutFormAttribute ( "at1Teste","desc","labelTeste","TEXT","." )
                .withCatalog ( catalog )
                .withManualRealizationActivity("Formulario3", attributeDTOList,colab)
                .withCriticality (listCriticidade.get(3))
                .withIcon ( icon )
                .build ();

        ServiceDraft draft4=new ServiceDraftBuilder ()
                .withServiceId ( "TesteConversaoDraftEmServico4" )
                .withTitle ( "Teste" )
                .withBriefDescription ( "Breve" )
                .withCompleteDescription ( "Completa" )
                .withKeyword ( "keywordTeste" )
                .withFilloutFormName ( "fillout" )
                .withFilloutFormAttribute ( "at1Teste","desc","labelTeste","TEXT","." )
                .withCatalog ( catalog )
                .withManualRealizationActivity("Formulario4", attributeDTOList,colab)
                .withCriticality (listCriticidade.get(3))
                .withIcon ( icon )
                .build ();

        icon = new byte[]{7, 8, 9};

        ServiceDraft draft5=new ServiceDraftBuilder ()
                .withServiceId ( "TesteConversaoDraftEmServico5" )
                .withTitle ( "Teste" )
                .withBriefDescription ( "Breve" )
                .withCompleteDescription ( "Completa" )
                .withKeyword ( "keywordTeste" )
                .withFilloutFormName ( "fillout" )
                .withFilloutFormAttribute ( "at1Teste","desc","labelTeste","TEXT","." )
                .withCatalog ( catalog )
                .withManualRealizationActivity("Formulario5", attributeDTOList,colab)
                .withCriticality (listCriticidade.get(3))
                .withIcon ( icon )
                .build ();

        PersistenceContext.repositories().serviceDrafts ().save ( draft1 );
        PersistenceContext.repositories().serviceDrafts ().save ( draft2 );
        PersistenceContext.repositories().serviceDrafts ().save ( draft3 );
        PersistenceContext.repositories().serviceDrafts ().save ( draft4 );
        PersistenceContext.repositories().serviceDrafts ().save ( draft5 );

        return true;
    }


    private Catalog getCatalog(Iterator<Catalog> catalogs) {
        if(catalogs==null || !catalogs.hasNext ()){
            LOGGER.log ( Level.INFO, "Catalog repository is empty" );
            return null;
        }
        return catalogs.next ();
    }

    private Iterator<Catalog> getCatalogs(){
        return catalogRepository.findAll ().iterator ();

    }

    private List<Criticidade> getCriticidades(){
        List<Criticidade> lista = new ArrayList<>();
        Iterable<Criticidade> Iterable = criticidadeRepository.findAll();
        for(Criticidade c : Iterable){
            lista.add(c);
        }
        return lista;
    }
}
