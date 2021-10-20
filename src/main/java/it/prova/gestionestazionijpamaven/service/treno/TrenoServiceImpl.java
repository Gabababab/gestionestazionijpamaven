package it.prova.gestionestazionijpamaven.service.treno;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionestazionijpamaven.dao.EntityManagerUtil;
import it.prova.gestionestazionijpamaven.dao.treno.TrenoDao;
import it.prova.gestionestazionijpamaven.model.Treno;

public class TrenoServiceImpl implements TrenoService {

	private TrenoDao trenoDaoInstance;
	
	@Override
	public List<Treno> listAllTreno() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			trenoDaoInstance.setEntityManager(entityManager);

			return trenoDaoInstance.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Treno caricaSingoloTreno(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			trenoDaoInstance.setEntityManager(entityManager);

			return trenoDaoInstance.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Treno trenoInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			trenoDaoInstance.setEntityManager(entityManager);

			trenoDaoInstance.update(trenoInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Treno trenoInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			trenoDaoInstance.setEntityManager(entityManager);

			trenoDaoInstance.insert(trenoInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Treno trenoInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			trenoDaoInstance.setEntityManager(entityManager);

			trenoDaoInstance.delete(trenoInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void setTrenoDao(TrenoDao trenoDaoInstance) {
		this.trenoDaoInstance=trenoDaoInstance;
	}

}
