package eapli.base.catalogmanagement.domain;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.common.Icon;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CatalogTest {

    Title title = new Title("abc");
    BriefDescription briefDesc = new BriefDescription("briefDesc");
    CompleteDescription completeDesc = new CompleteDescription("completeDesc");
    Icon icon = new Icon(new byte[] {1,2,3});

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullParameterIsNotAllowed() {
        Catalog catalog = new Catalog((Title) null, null, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullResponsavelIsNotAllowed() {
        Catalog catalog = new Catalog(title, briefDesc, completeDesc, icon, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyIconIsNotAllowed() {
        Catalog catalog = new Catalog("title", "briefDesc", "completeDesc", new byte[] {}, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyAllowedTeamsIsNotAllowed() {
        Catalog catalog = new Catalog(title, briefDesc, completeDesc, icon, null, new ArrayList<>());
    }

}