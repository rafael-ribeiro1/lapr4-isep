package eapli.base.colaboratormanagement.application;


import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.ColaboradorBuilder;
import eapli.base.colaboratormanagement.domain.Funcao;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.repository.EquipaRepository;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.RandomPasswordGenerator;
import eapli.base.utils.EmailHandler;
import eapli.framework.application.ApplicationService;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.time.util.Calendars;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@ApplicationService
public class RegisterCollaboratorService {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final SystemUserBuilder userBuilder = new SystemUserBuilder(new BasePasswordPolicy(), new PlainTextEncoder());
    private final ColaboradorBuilder builder = new ColaboradorBuilder();
    private final RepositoryFactory factory = PersistenceContext.repositories();

    private final EmailHandler emailHandler = new EmailHandler();

    public Colaborador registerColaborator(String username, String instiEmail, String numMeca, String firstName, String lastName, String nomeCompleto,
                                           Calendar birthDate, String distrito, String concelho, String contactNumber, Funcao function, Colaborador selectedResponsavel,
                                           Set<Equipa> selectedTeams){

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.RESPONSAVEL_RH,BaseRoles.POWER_USER);

        String password = RandomPasswordGenerator.generateStrongPassword();

        Set<Role> role = new HashSet<>(); role.add(BaseRoles.COLABORADOR);  role.add(BaseRoles.CLIENT_USER);

        final SystemUser newUser =  userBuilder.with(username, password, firstName, lastName, instiEmail).createdOn(Calendars.now()).withRoles(role).build();


        if(!validateEquipasColaborador(selectedTeams))  throw  new IllegalArgumentException("Invalid Teams for Colaborator!");

        final TransactionalContext ctx = factory.newTransactionalContext();

        final ColaboradorRepository colaboradorRepository = factory.colaboradores(ctx);
        final UserRepository userRepository = factory.users(ctx);
        final EquipaRepository equipaRepository = factory.equipas(ctx);

        ctx.beginTransaction();

        SystemUser registerUser = userRepository.save(newUser);
        final Colaborador newColaborador = builder.with(registerUser,numMeca,birthDate,firstName,lastName,nomeCompleto,distrito,concelho,contactNumber,instiEmail,selectedResponsavel,function).build();

        Colaborador c = colaboradorRepository.save(newColaborador);

        for (Equipa e : selectedTeams) {
            e.adicionarColaboradorEquipa(c);
            equipaRepository.save(e);
        }

        ctx.commit();
        ctx.close();


        emailHandler.sendEmail(c.emailInstitucional().toString(), "Register in Help Desk Platform",
                    String.format("Hello %s,%nYou were registered in the plataform %nUse this password: %s to enter the platform", c.completeName(),password));



        return newColaborador;
    }

    public boolean validateEquipasColaborador(Set<Equipa> listaEquipas) {
        if(listaEquipas.isEmpty()) return true;
        for(Equipa firstTeam : listaEquipas){
            for(Equipa secondTeam : listaEquipas){
                if(!secondTeam.equals(firstTeam) && secondTeam.tipoEquipa().equals(firstTeam.tipoEquipa()) ){
                    return false;
                }
            }
        }
        return true;
    }

}
