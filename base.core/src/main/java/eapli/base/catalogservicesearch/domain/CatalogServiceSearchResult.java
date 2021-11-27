package eapli.base.catalogservicesearch.domain;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.servicemanagement.domain.Service;
import eapli.base.servicemanagement.domain.ServiceDraft;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CatalogServiceSearchResult {

    private List<Catalog> catalogs;
    private List<Service> services;
    private List<ServiceDraft> serviceDrafts;

    public CatalogServiceSearchResult(List<Catalog> catalogs, List<Service> services, List<ServiceDraft> serviceDrafts) {
        if (catalogs == null || services == null || serviceDrafts == null)
            throw new IllegalArgumentException("Search list should not be null");
        this.catalogs = catalogs;
        this.services = services;
        this.serviceDrafts = serviceDrafts;
    }

    public List<Catalog> catalogs() {
        Set<Catalog> catalogList = new HashSet<>(catalogs);
        for (Service s : services)
            catalogList.add(s.catalog());
        for (ServiceDraft sd : serviceDrafts)
            catalogList.add(sd.catalog());
        return new ArrayList<>(catalogList);
    }

    public List<Service> servicesOfCatalog(Catalog catalog) {
        List<Service> serviceList = new ArrayList<>();
        for (Service s : services) {
            if (catalog == null) {
                if (s.catalog() == null)
                    serviceList.add(s);
            } else {
                if (catalog.sameAs(s.catalog()))
                    serviceList.add(s);
            }
        }
        return serviceList;
    }

    public List<ServiceDraft> serviceDraftsOfCatalog(Catalog catalog) {
        List<ServiceDraft> serviceList = new ArrayList<>();
        for (ServiceDraft s : serviceDrafts) {
            if (catalog == null) {
                if (s.catalog() == null)
                    serviceList.add(s);
            } else {
                if (catalog.sameAs(s.catalog()))
                    serviceList.add(s);
            }
        }
        return serviceList;
    }

}
