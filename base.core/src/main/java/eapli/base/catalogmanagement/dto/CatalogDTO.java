package eapli.base.catalogmanagement.dto;

import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.framework.representations.dto.DTO;

@DTO
public class CatalogDTO {
    public Long id; //allow to obtain the catalog object from the database
    public String title;
    public String briefDescription;
    public Criticidade criticidade;

    public CatalogDTO(Long id,String title, String briefDescription) {
        this.id=id;
        this.title = title;
        this.briefDescription = briefDescription;
    }

    public CatalogDTO(Long id,String title, String briefDescription,Criticidade criticidade) {
        this.id=id;
        this.title = title;
        this.briefDescription = briefDescription;
        this.criticidade=criticidade;
    }

    public CatalogDTO() {
        //Empty
    }

    @Override
    public String toString() {
        return String.format ( "Title: %s, Description: %s",title, briefDescription );
    }
}
