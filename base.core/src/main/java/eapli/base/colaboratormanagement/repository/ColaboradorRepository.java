package eapli.base.colaboratormanagement.repository;

import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.EmailInstitucional;
import eapli.base.colaboratormanagement.domain.NumeroMecanografico;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.domain.TipoEquipa;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.representations.dto.DTOable;

import java.util.List;
import java.util.Optional;

public interface ColaboradorRepository extends DomainRepository<Long, Colaborador> {
    Iterable<Colaborador> findAll();

    Colaborador findColaboradorPorNumeroMecanografico(NumeroMecanografico numeroMecanografico);

    Iterable<Colaborador> findAllColaboradoresPorEquipa(Equipa e);

    Iterable<Colaborador> findAllColaboradoresSemTipoEquipa(TipoEquipa t);

    Iterable<Colaborador> findAllResponsaveisSemTipoEquipa(TipoEquipa t);

    Iterable<Colaborador> findAllResponsaveisColaboradoresSemTipoEquipa(TipoEquipa t);

    Colaborador findColaboradorPorEmail(EmailInstitucional emailInstitucional);
}
