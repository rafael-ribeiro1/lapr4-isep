package eapli.base.colaboratormanagement.dto;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.EmailInstitucional;
import eapli.base.colaboratormanagement.domain.NumeroMecanografico;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.framework.representations.dto.DTOParser;

public class ColaboradorDTOParser implements DTOParser<ColaboradorDTO, Colaborador> {

    private final ColaboradorRepository repository;


    public ColaboradorDTOParser(final ColaboradorRepository repository){this.repository=repository;}


    @Override
    public Colaborador valueOf(ColaboradorDTO dto) {
        if(dto==null) return null;
        return repository.findColaboradorPorEmail(new EmailInstitucional(dto.email));
    }
}
