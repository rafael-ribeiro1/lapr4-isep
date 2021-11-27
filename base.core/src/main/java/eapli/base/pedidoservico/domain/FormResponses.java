package eapli.base.pedidoservico.domain;

import eapli.base.servicemanagement.domain.form.Attribute;
import eapli.base.utils.ANSI;
import eapli.framework.domain.model.ValueObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class FormResponses implements ValueObject {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private final Map<Attribute, String> responses;

    protected FormResponses() {
        this.responses = new HashMap<>();
    }

    public FormResponses(Map<Attribute, String> responses) {
        if (responses == null)
            throw new IllegalArgumentException("Responses map is null");

        for (Map.Entry<Attribute,String> entry : responses.entrySet()) {
            Attribute attr = entry.getKey();
            String response = entry.getValue();
            if (!response.matches(attr.regex()))
                throw new IllegalArgumentException("Invalid response to attribute " + attr.label());
        }

        this.responses = new HashMap<>(responses);
    }
    public String obtainResponse(Attribute attribute){
        return responses.get(attribute);
    }

}
