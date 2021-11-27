package eapli.base.colaboratormanagement.dto;

import eapli.framework.representations.dto.DTO;

@DTO
public class ColaboradorDTO {

    public String email;

    public String numeroMecanografico;

    public String nome;


    public ColaboradorDTO(){
        // empty
    }

    public ColaboradorDTO(final String email , final String numeroMecanografico , final String nome ){
        this.email=email;
        this.numeroMecanografico=numeroMecanografico;
        this.nome=nome;
    }


    @Override
    public String toString() {
        return String.format("%s que pertence a %s",email,nome);
    }
}
