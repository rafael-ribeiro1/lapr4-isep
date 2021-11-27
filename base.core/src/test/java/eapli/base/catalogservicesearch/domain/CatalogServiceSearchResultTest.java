package eapli.base.catalogservicesearch.domain;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.ServiceDraft;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CatalogServiceSearchResultTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNoParameterIsNull1() {
        List<Service> services = new ArrayList<>();
        List<ServiceDraft> serviceDrafts = new ArrayList<>();
        CatalogServiceSearchResult result = new CatalogServiceSearchResult(null, services, serviceDrafts);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNoParameterIsNull2() {
        List<Catalog> catalogs = new ArrayList<>();
        List<ServiceDraft> serviceDrafts = new ArrayList<>();
        CatalogServiceSearchResult result = new CatalogServiceSearchResult(catalogs, null, serviceDrafts);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNoParameterIsNull3() {
        List<Catalog> catalogs = new ArrayList<>();
        List<Service> services = new ArrayList<>();
        CatalogServiceSearchResult result = new CatalogServiceSearchResult(catalogs, services, null);
    }

}