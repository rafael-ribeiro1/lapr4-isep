package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.colaboratormanagement.repository.FuncaoRepository;
import eapli.base.contratoSLAmanagement.repository.ContratoSLARepository;
import eapli.base.criticidademanagement.repository.CriticidadeRepository;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.pedidoservico.repository.PedidoRepository;
import eapli.base.servicemanagement.repository.ServiceRepository;
import eapli.base.servicemanagement.repository.ServiceDraftRepository;
import eapli.base.teammanagement.repository.EquipaRepository;
import eapli.base.teammanagement.repository.TipoEquipaRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

	@Override
	public UserRepository users(final TransactionalContext autoTx) {
		return new JpaAutoTxUserRepository(autoTx);
	}

	@Override
	public UserRepository users() {
		return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
				Application.settings().getExtendedPersistenceProperties());
	}


	@Override
	public JpaClientUserRepository clientUsers(final TransactionalContext autoTx) {
		return new JpaClientUserRepository(autoTx);
	}

	@Override
	public JpaClientUserRepository clientUsers() {
		return new JpaClientUserRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
		return new JpaSignupRequestRepository(autoTx);
	}

	@Override
	public SignupRequestRepository signupRequests() {
		return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public EquipaRepository equipas(final TransactionalContext autoTx) {
		return new JpaEquipaRepository(autoTx);
	}


	@Override
	public EquipaRepository equipas() {
		return new JpaEquipaRepository();
	}

	@Override
	public TipoEquipaRepository tiposEquipa() {
		return new JpaTipoEquipaRepository();
	}

	@Override
	public TransactionalContext newTransactionalContext() {
		return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
				Application.settings().getExtendedPersistenceProperties());
	}

	@Override
	public JpaCatalogRepository catalogs() {
		return new JpaCatalogRepository();
	}

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	@Override
	public ColaboradorRepository colaboradores() {
		return new JpaColaboradorRepository();
	}

	/**
	 * @param autoTx the transactional context to enroll
	 * @return
	 */

	@Override
	public ColaboradorRepository colaboradores(TransactionalContext autoTx) {
		return new JpaColaboradorRepository(autoTx);
	}

	@Override
	public ServiceDraftRepository serviceDrafts(){
		return new JpaSpecifyServiceDraftRepository ();
	}

	@Override
	public FuncaoRepository funcoes() {
		return new JpaFuncaoRepository();
	}

	@Override
	public CriticidadeRepository criticidades() {return new JpaCriticidadeRepository();}

	@Override
	public ServiceRepository services() {
		return new JpaSpecifyServiceRepository();
	}

	@Override
	public ContratoSLARepository contratosSLA() {
		return new JpaContratoSLARepository();
	}

	@Override
	public PedidoRepository pedidos() {
		return new JpaPedidoRepository();
	}


}
