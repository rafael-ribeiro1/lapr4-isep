package eapli.base.servicemanagement.dto;

import eapli.base.catalogmanagement.dto.CatalogDTO;
import eapli.base.servicemanagement.domain.FluxoAtividadesServico;
import eapli.framework.representations.dto.DTO;

import java.util.ArrayList;
import java.util.List;

@DTO
public class ServiceDraftDTO {
    public String title;
    public String serviceCode;
    public byte[] icon;
    public String briefDesc;
    public String completeDesc;
    public List<String> keywords;
    public CatalogDTO catalog;
    public String fluxInfo;

    public ServiceDraftDTO(final String title,final String serviceCode,final byte[] icon,final String briefDesc,
                           final String completeDesc,final List<String> keywords, final CatalogDTO catalog,final String flux) {
        this.title = title;
        this.serviceCode = serviceCode;
        this.icon = icon;
        this.briefDesc = briefDesc;
        this.completeDesc = completeDesc;
        this.keywords = new ArrayList<>(keywords);
        this.catalog = catalog;
        this.fluxInfo=flux;
    }

    public ServiceDraftDTO() {
        keywords=new ArrayList<> ();
    }

    @Override
    public String toString() {
        return String.format ( "Title: %s, Service Code: %s",title,serviceCode );
    }
}
