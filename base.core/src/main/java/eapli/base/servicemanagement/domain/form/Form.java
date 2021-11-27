package eapli.base.servicemanagement.domain.form;

import eapli.base.activity.domain.Script;
import eapli.base.antlr.formulario.ValidateFormAPI;
import eapli.base.antlr.formulario.ValidateFormListenerAPI;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given JPA constraints, this value object has to be created as an entity in order to be embedded
 * in the Service and ServiceDraft classes
 */

@Entity
public class Form {
    @Version
    private Long version;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Embedded
    private ServiceLevelId serviceLevelId;
    @Embedded
    private Name name;
    @OneToMany(cascade=CascadeType.ALL)
    private final List<Attribute> lAttributes;
    @Embedded
    private Script script;

    public Form(ServiceLevelId serviceLevelId, Name name, List<Attribute> lAttributes, Script script) {
        if(name==null)
            throw new IllegalArgumentException ("Name cannot be null");
        if(serviceLevelId==null)
            throw new IllegalArgumentException ("Service level id of the form cannot be null");
        if(lAttributes==null || lAttributes.isEmpty ())
            throw new IllegalArgumentException ("Form must have at least 1 attribute");
        if (script == null)
            throw new IllegalArgumentException("Script is null");
        Map<Attribute, String> formAttr = new HashMap<>();
        for (Attribute attr : lAttributes) formAttr.put(attr, "");
        ValidateFormAPI api = new ValidateFormListenerAPI(script.toString(), formAttr);
        if (!api.validateScript())
            throw new IllegalArgumentException("Script is invalid");
        this.serviceLevelId = serviceLevelId;
        this.name = name;
        this.lAttributes = new ArrayList<> (lAttributes);
        this.script = script;
    }

    public Form(ServiceLevelId serviceLevelId, Name name, List<Attribute> lAttributes) {
        if(name==null)
            throw new IllegalArgumentException ("Name cannot be null");
        if(serviceLevelId==null)
            throw new IllegalArgumentException ("Service level id of the form cannot be null");
        if(lAttributes==null || lAttributes.isEmpty ())
            throw new IllegalArgumentException ("Form must have at least 1 attribute");
        this.serviceLevelId = serviceLevelId;
        this.name = name;
        this.lAttributes = new ArrayList<> (lAttributes);
    }


    public Form(Name name, List<Attribute> lAttributes) {
        if(name==null)
            throw new IllegalArgumentException ("Name cannot be null");
        this.name = name;
        this.lAttributes = new ArrayList<> (lAttributes);
    }

    protected Form() {
        /*ORM*/
        this.lAttributes=new ArrayList<> ();
    }

    public List<Attribute> attributes() {
        return new ArrayList<>(this.lAttributes);
    }

    public Script script() {
        return this.script;
    }

    @Override
    public String toString() {
        return "Formulario{" +
                //"id=" + id +
                ",\nnome=" + name +
                //",\ncampos=" + lAttributes +
                '}';
    }
}
