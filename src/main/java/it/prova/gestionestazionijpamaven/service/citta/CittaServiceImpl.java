package it.prova.gestionestazionijpamaven.service.citta;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionestazionijpamaven.dao.EntityManagerUtil;
import it.prova.gestionestazionijpamaven.dao.citta.CittaDao;
import it.prova.gestionestazionijpamaven.model.Citta;

public class CittaServiceImpl implements CittaService{

	private CittaDao cittaDaoInstance;
	
	@Override
	public List<Citta> listAllCitta() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			cittaDaoInstance.setEntityManager(entityManager);

			return cittaDaoInstance.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Citta caricaSingoloCitta(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			cittaDaoInstance.setEntityManager(entityManager);

			return cittaDaoInstance.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Citta cittaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cittaDaoInstance.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			cittaDaoInstance.update(cittaInstance);

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
	public void inserisciNuovo(Citta cittaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cittaDaoInstance.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			cittaDaoInstance.insert(cittaInstance);

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
	public void rimuovi(Citta cittaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cittaDaoInstance.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			cittaDaoInstance.delete(cittaInstance);

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
	public void setCittaDao(CittaDao cittaDaoInstance) {
		this.cittaDaoInstance=cittaDaoInstance;
	}

	@Override
	public Citta caricaEagerConStazioni(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			cittaDaoInstance.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return cittaDaoInstance.findByFetchingStazioni(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Integer> ricercaAbitantiByTreno(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			cittaDaoInstance.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return cittaDaoInstance.findAllAbitantiByTreno(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

}
