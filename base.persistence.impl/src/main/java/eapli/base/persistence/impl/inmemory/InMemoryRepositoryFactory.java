package eapli.base.persistence.impl.inmemory;

import eapli.base.catalogmanagement.repository.CatalogRepository;
import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.colaboratormanagement.repository.FuncaoRepository;
import eapli.base.contratoSLAmanagement.repository.ContratoSLARepository;
import eapli.base.criticidademanagement.repository.CriticidadeRepository;
import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.pedidoservico.repository.PedidoRepository;
import eapli.base.persistence.impl.jpa.JpaEquipaRepository;
import eapli.base.servicemanagement.repository.ServiceRepository;
import eapli.base.servicemanagement.repository.ServiceDraftRepository;
import eapli.base.teammanagement.repository.EquipaRepository;
import eapli.base.teammanagement.repository.TipoEquipaRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.InMemoryUserRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

	static {
		// only needed because of the in memory persistence
		new BaseBootstrapper().execute();
	}

	@Override
	public UserRepository users(final TransactionalContext tx) {
		return new InMemoryUserRepository();
	}

	@Override
	public UserRepository users() {
		return users(null);
	}


	@Override
	public ClientUserRepository clientUsers(final TransactionalContext tx) {

		return new InMemoryClientUserRepository();
	}

	@Override
	public ClientUserRepository clientUsers() {
		return clientUsers(null);
	}

	@Override
	public SignupRequestRepository signupRequests() {
		return signupRequests(null);
	}

	@Override
	public EquipaRepository equipas(TransactionalContext autoTx) {
		return new JpaEquipaRepository();
	}

	@Override
	public EquipaRepository equipas() {
		return new InMemoryEquipaRepository();
	}

	@Override
	public SignupRequestRepository signupRequests(final TransactionalContext tx) {
		return new InMemorySignupRequestRepository();
	}


	@Override
	public TransactionalContext newTransactionalContext() {
		// in memory does not support transactions...
		return null;
	}

	@Override
	public CatalogRepository catalogs() {
		return new InMemoryCatalogRepository();
	}

	@Override
	public ColaboradorRepository colaboradores(final TransactionalContext tx){ return  new InMemoryColaboradorRepository();}

	@Override
	public TipoEquipaRepository tiposEquipa() {
		return new InMemoryTipoEquipaRepository();
	}

	@Override
	public FuncaoRepository funcoes() {
		return new InMemoryFuncaoRepository();
	}

	@Override
	public ServiceDraftRepository serviceDrafts() {
		return new InMemoryServiceDraftRepository();
	}

	@Override
	public CriticidadeRepository criticidades(){
		return new InMemoryCriticidadeRepository();
	}

	@Override
	public ServiceRepository services() {
		return new InMemorySpecifyServiceRepository();
	}

	@Override
	public ContratoSLARepository contratosSLA() {return new InMemoryContratoSLARepository(); }

	@Override
	public PedidoRepository pedidos() {
		return new InMemoryPedidoRepository();
	}

	@Override
	public ColaboradorRepository colaboradores(){return  colaboradores(null);}

}
