package eapli.base.app.backoffice.console.presentation.service;

import eapli.base.servicemanagement.domain.form.AttributeDTO;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.function.Function;

public class AddFormAttributeUI extends AbstractUI {
    private final Function<AttributeDTO,Void> addAttributeFunction;
    private final AttributeDTO dto;

    public AddFormAttributeUI(Function<AttributeDTO, Void> addAttributeFunction) {
        this.addAttributeFunction = addAttributeFunction;
        this.dto=new AttributeDTO ();
    }

    @Override
    protected boolean doShow() {
        try{
            final String name= Console.readLine ("Insert Attribute's name");
            final String desc= Console.readLine ("Insert Attribute's description");
            final String type=chooseType ();
            final String label=Console.readLine ("Insert Attribute's label");
            final String regex=Console.readLine ("Insert Attribute's regular expression");
            this.dto.name=name;
            this.dto.description=desc;
            this.dto.dataType=type;
            this.dto.label=label;
            this.dto.regex=regex;
            this.addAttributeFunction.apply ( dto );
            System.out.println ("Attribute added");
        }catch (IllegalArgumentException | ConcurrencyException e){
            System.out.println (e.getMessage ());
            Console.readLine ( "Press Enter to continue" );
        }
        return true;
    }

    private String chooseType(){
        int i;
        do{
            i=Console.readInteger ( "\"0:Number\n1:Date\n2:Text\n3:Boolean\nChoose attribute's type" );
            switch (i){
                case 0: return "NUMBER";
                case 1: return "DATE";
                case 2: return "TEXT";
                case 3: return "BOOLEAN";
                default: System.out.println ("Invalid Input");
            }
        }while (true);
    }

    @Override
    public String headline() {
        return "Add Form Attribute";
    }

    @Override
    public String toString() {
        return headline ();
    }
}
