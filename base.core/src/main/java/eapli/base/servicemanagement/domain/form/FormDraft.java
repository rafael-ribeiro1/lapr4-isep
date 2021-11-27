package eapli.base.servicemanagement.domain.form;

import eapli.base.activity.domain.Script;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class FormDraft {
    @Version
    private Long version;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Embedded
    private ServiceLevelId serviceLevelId;
    @Embedded
    private Name name;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Attribute> lAttributes =new LinkedList<> ();
    @Embedded
    private Script script;

    public Form build() {
        if (script == null)
            return new Form (serviceLevelId,name,lAttributes);
        else
            return new Form(serviceLevelId,name,lAttributes,script);
    }

    public FormDraft withServiceLevelId(final String id){
        this.serviceLevelId= new ServiceLevelId (id);
        return this;
    }

    public FormDraft withName(final String name){
        this.name=new Name (name);
        return this;
    }

    public FormDraft withAttribute(final String name, final  String description,
                                   final String label, final String dataType, final String regex){
        try{
            Attribute a=Attribute.create ( name,description,label,dataType,regex );
            lAttributes.add ( a );
        }catch (IllegalArgumentException e){
            System.out.println ("Could not add attribute to form");
            System.out.println (e.getMessage ());
        }
        return this;
    }

    public FormDraft withScript(final byte[] script) {
        this.script = new Script(script);
        return this;
    }
}
