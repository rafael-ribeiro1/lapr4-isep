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

public class ServiceBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger ( ServiceDraftBootstrapper.class );

    private static final String DEFAULT_ICON_PATH = "images/isep.png";

    private final CatalogRepository catalogRepository = PersistenceContext.repositories ().catalogs ();
    private final EquipaRepository equipaRepository = PersistenceContext.repositories ().equipas ();

    private final CriticidadeRepository criticidadeRepository = PersistenceContext.repositories ().criticidades ();

    @Override
    public boolean execute() {
        Catalog catalog;
        Iterator<Catalog> catalogs = getCatalogs ();

        catalog = getCatalog ( catalogs );

        byte[] icon = new byte[]{4, 5, 6};

        List<AttributeDTO> attributeDTOList = new ArrayList<> ();
        // String name, String description, String label, String dataType, String regex
        AttributeDTO attributeDTO = new AttributeDTO ( "Atributo", "Descricao", "Label a", "TEXT", "[a-zA-Z]*" );
        attributeDTOList.add ( attributeDTO );

        List<Equipa> listEquipa = new ArrayList<> ( Arrays.asList ( equipaRepository
                .findEquipaPorCodigoUnico ( CodigoUnico.valueOf ( "DF1" ) ),
                equipaRepository.findEquipaPorCodigoUnico ( CodigoUnico.valueOf ( "DRH1" ) ),
                equipaRepository.findEquipaPorCodigoUnico ( CodigoUnico.valueOf ( "DC1" ) ) ) );

        List<Criticidade> listCriticidade = this.getCriticidades ();

        ServiceDraft draft1 = null;
        try {
            draft1 = new ServiceDraftBuilder ()
                    .withServiceId ( "VendasServicos1" )
                    .withTitle ( "Requerer cotação para venda por grosso" )
                    .withBriefDescription ( "Requisitar valores de compra de produtos" )
                    .withCompleteDescription ( "Requisitar preços de produtos, juntamente com a quantidade desejada" )
                    .withKeyword ( "cotação" )
                    .withKeyword ( "vendas" )
                    .withKeyword ( "grosso" )
                    .withFilloutFormName ( "Formulario Cotacao" )
                    .withFilloutFormAttribute ( "info", "Especifique informacao", "cotacaoInfo", "TEXT", "[a-zA-Z0-9 ]*" )
                    .withCatalog ( catalog )
                    .withIcon ( icon )
                    .withAutomaticRealizationActivity ( Files.readAllBytes ( Paths.get ( "scripts/script1.txt" ) ) )
                    .withCriticality ( listCriticidade.get ( 0 ) )
                    .build ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

        ServiceDraft draft2 = new ServiceDraftBuilder ()
                .withServiceId ( "VendasServicos2" )
                .withTitle ( "Solicitar autorização de desconto financeiros" )
                .withBriefDescription ( "Obter autorização para aplicar desconto" )
                .withCompleteDescription ( "Requisitar desconto financeiro relativo a uma venda. Dependendo do pedido, este processo pode demorar até 2 dias úteis a ser respondido." )
                .withKeyword ( "descontos" )
                .withFilloutFormName ( "Formulario Descontos" )
                .withFilloutFormAttribute ( "info", "Especifique o desconto", "descontoInfo", "TEXT", "[a-zA-Z0-9 ]*" )
                .withCatalog ( catalog )
                .withIcon ( icon )
                .withManualRealizationActivity ( "Formulario1", attributeDTOList, listEquipa )
                .withCriticality ( listCriticidade.get ( 1 ) )
                .build ();

        catalog = getCatalog ( catalogs );
        icon = new byte[]{7, 8, 9};

        Colaborador colab = PersistenceContext.repositories ().colaboradores ().findColaboradorPorEmail ( new EmailInstitucional ( "1190549@isep.ipp.pt" ) );

        ServiceDraft draft3 = new ServiceDraftBuilder ()
                .withServiceId ( "AnomaliaEquipamento1" )
                .withTitle ( "Registar Avaria" )
                .withBriefDescription ( "Registar avaria de um equipamento" )
                .withCompleteDescription ( "Registar uma avaria de um equipamento da empresa. Deve indicar o identificador do equipamento, assim como outros detalhes" )
                .withKeyword ( "avaria" )
                .withKeyword ( "equipamento" )
                .withFilloutFormName ( "Formulario de Preenchimento de Avaria" )
                .withFilloutFormAttribute ( "info", "Especifique a avaria", "avariaInfo", "TEXT", "[a-zA-Z0-9 ]*" )
                .withCatalog ( catalog )
                .withManualRealizationActivity ( "Formulario2", attributeDTOList, colab )
                .withCriticality ( listCriticidade.get ( 2 ) )
                .withIcon ( icon )
                .build ();


        ServiceDraft draft4 = new ServiceDraftBuilder ()
                .withServiceId ( "AnomaliaComunicacao1" )
                .withTitle ( "Reportar anomalia" )
                .withBriefDescription ( "Reportar anomalia de comunicação" )
                .withCompleteDescription ( "Registar uma anomalia de comunicação. Deve indicar a data do ocorrido, assim como outros detalhes" )
                .withKeyword ( "anomalia" )
                .withFilloutFormName ( "Formulario Comunicacao" )
                .withFilloutFormAttribute ( "info", "Especifique a anomalia", "anomaliaInfo", "TEXT", "[a-zA-Z0-9 ]*" )
                .withCatalog ( catalog )
                .withIcon ( icon )
                .withManualRealizationActivity ( "Formulario4", attributeDTOList, listEquipa )
                .withCriticality ( listCriticidade.get ( 3 ) )
                .build ();

        ServiceDraft draft5 = new ServiceDraftBuilder ()
                .withServiceId ( "AnomaliaAplicacao1" )
                .withTitle ( "Reportar anomalia" )
                .withBriefDescription ( "Reportar anomalia em aplicação" )
                .withCompleteDescription ( "Registar uma anomalia de aplicação. Deve indicar a data do ocorrido, assim como outros detalhes" )
                .withKeyword ( "anomalia" )
                .withFilloutFormName ( "Formulario Aplicacao" )
                .withFilloutFormAttribute ( "info", "Especifique a anomalia", "anomaliaInfo", "TEXT", "[a-zA-Z0-9 ]*" )
                .withCatalog ( catalog )
                .withIcon ( icon )
                .withManualRealizationActivity ( "Formulario5", attributeDTOList, listEquipa )
                .withCriticality ( listCriticidade.get ( 4 ) )
                .build ();

        ServiceDraft draft6 = new ServiceDraftBuilder ()
                .withServiceId ( "AnomaliaAplicacao2" )
                .withTitle ( "Reportar anomalia" )
                .withBriefDescription ( "Reportar anomalia em aplicação versao 2" )
                .withCompleteDescription ( "Registar uma anomalia de aplicação versao 2" )
                .withKeyword ( "anomalia" )
                .withFilloutFormName ( "Formulario Aplicacao" )
                .withFilloutFormAttribute ( "info", "Especifique a anomalia", "anomaliaInfo", "TEXT", "[a-zA-Z0-9 ]*" )
                .withCatalog ( catalog )
                .withIcon ( icon )
                .withApprovalActivity ( "RESPONSAVEL_CATALOGO" )
                .withManualRealizationActivity ( "Formulario6", attributeDTOList, listEquipa )
                .withCriticality ( listCriticidade.get ( 5 ) )
                .build ();

        ServiceDraft draft7 = new ServiceDraftBuilder ()
                .withServiceId ( "AusenciaPreco" )
                .withTitle ( "Requisitar preco" )
                .withBriefDescription ( "Pedir preco que esta a faltar" )
                .withCompleteDescription ( "Pedir preco que esta a faltar em um produto" )
                .withKeyword ( "preco" )
                .withFilloutFormName ( "Formulario Aplicacao" )
                .withFilloutFormAttribute ( "info", "Especifique a anomalia", "anomaliaInfo", "TEXT", "[a-zA-Z0-9 ]*" )
                .withCatalog ( catalog )
                .withIcon ( icon )
                .withApprovalActivity ( "RESPONSAVEL_HIERARQUICO" )
                .withManualRealizationActivity ( "Formulario6", attributeDTOList, listEquipa )
                .withCriticality ( listCriticidade.get ( 2 ) )
                .build ();


        ServiceDraft draft8 = new ServiceDraftBuilder ()
                .withServiceId ( "PedirPreco" )
                .withTitle ( "Pedir preco de um produto" )
                .withBriefDescription ( "Pedir preco de um produto desconhecido" )
                .withCompleteDescription ( "Pedir preco de um produto desconhecido a faltar" )
                .withKeyword ( "produto" )
                .withFilloutFormName ( "Formulario Aplicacao" )
                .withFilloutFormAttribute ( "info", "Especifique a anomalia", "anomaliaInfo", "TEXT", "[a-zA-Z0-9 ]*" )
                .withCatalog ( catalog )
                .withIcon ( icon )
                .withApprovalActivity ( "RESPONSAVEL_HIERARQUICO" )
                .withManualRealizationActivity ( "Formulario6", attributeDTOList, listEquipa )
                .withCriticality ( listCriticidade.get ( 3 ) )
                .build ();


        PersistenceContext.repositories ().services ().save ( draft1.build () );
        PersistenceContext.repositories ().services ().save ( draft2.build () );
        PersistenceContext.repositories ().services ().save ( draft3.build () );
        PersistenceContext.repositories ().services ().save ( draft4.build () );
        PersistenceContext.repositories ().services ().save ( draft5.build () );
        PersistenceContext.repositories ().services ().save ( draft6.build () );
        PersistenceContext.repositories ().services ().save ( draft7.build () );
        PersistenceContext.repositories ().services ().save ( draft8.build () );

        return true;
    }


    private Catalog getCatalog(Iterator<Catalog> catalogs) {
        if (catalogs == null || !catalogs.hasNext ()) {
            LOGGER.log ( Level.INFO, "Catalog repository is empty" );
            return null;
        }
        return catalogs.next ();
    }

    private Iterator<Catalog> getCatalogs() {
        return catalogRepository.findAll ().iterator ();

    }

    private List<Criticidade> getCriticidades() {
        List<Criticidade> lista = new ArrayList<> ();
        Iterable<Criticidade> Iterable = criticidadeRepository.findAll ();
        for (Criticidade c : Iterable) {
            lista.add ( c );
        }
        return lista;
    }
}
