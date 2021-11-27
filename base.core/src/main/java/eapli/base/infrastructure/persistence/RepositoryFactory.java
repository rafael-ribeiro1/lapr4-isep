/**
 *
 */
package eapli.base.infrastructure.persistence;

import eapli.base.catalogmanagement.repository.CatalogRepository;
import eapli.base.clientusermanagement.repositories.ClientUserRepository;

import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.colaboratormanagement.repository.ColaboradorRepository;
import eapli.base.colaboratormanagement.repository.FuncaoRepository;
import eapli.base.contratoSLAmanagement.repository.ContratoSLARepository;
import eapli.base.criticidademanagement.repository.CriticidadeRepository;
import eapli.base.pedidoservico.repository.PedidoRepository;
import eapli.base.servicemanagement.repository.ServiceRepository;
import eapli.base.servicemanagement.repository.ServiceDraftRepository;
import eapli.base.teammanagement.repository.EquipaRepository;
import eapli.base.teammanagement.repository.TipoEquipaRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

	/**
	 * factory method to create a transactional context to use in the repositories
	 *
	 * @return
	 */
	TransactionalContext newTransactionalContext();

	/**
	 *
	 * @param autoTx the transactional context to enrol
	 * @return
	 */
	UserRepository users(TransactionalContext autoTx);

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	UserRepository users();

	/**
	 *
	 * @param autoTx the transactional context to enroll
	 * @return
	 */
	ClientUserRepository clientUsers(TransactionalContext autoTx);

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	ClientUserRepository clientUsers();

	/**
	 *
	 * @param autoTx the transactional context to enroll
	 * @return
	 */
	SignupRequestRepository signupRequests(TransactionalContext autoTx);

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	SignupRequestRepository signupRequests();

	/**
	 * @param autoTx the transactional context to enroll
	 *
	 * @return
	 */
	EquipaRepository equipas(TransactionalContext autoTx);

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	EquipaRepository equipas();

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	CatalogRepository catalogs();
	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	ColaboradorRepository colaboradores();

	/**
	 *
	 * @param autoTx the transactional context to enroll
	 * @return
	 */
	ColaboradorRepository colaboradores(TransactionalContext autoTx);

	/**
	 * repository will be created in auto transaction mode
	 *
	 * @return
	 */
	TipoEquipaRepository tiposEquipa();

	ServiceDraftRepository serviceDrafts();

	FuncaoRepository funcoes();

	CriticidadeRepository criticidades();

	ServiceRepository services();

	ContratoSLARepository contratosSLA();

	PedidoRepository pedidos();
}
