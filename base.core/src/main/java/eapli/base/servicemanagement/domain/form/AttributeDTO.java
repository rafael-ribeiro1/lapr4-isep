package eapli.base.servicemanagement.domain.form;

import eapli.framework.general.domain.model.Description;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class AttributeDTO {
    public String name;
    public String description;
    public String label;
    public String dataType;
    public String regex;

    public AttributeDTO(String name, String description, String label, String dataType, String regex) {
        this.name = name;
        this.description = description;
        this.label = label;
        this.dataType = dataType;
        this.regex = regex;
    }

    public AttributeDTO() {
    }
}
