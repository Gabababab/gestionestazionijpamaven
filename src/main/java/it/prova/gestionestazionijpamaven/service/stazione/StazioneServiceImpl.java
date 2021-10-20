package it.prova.gestionestazionijpamaven.service.stazione;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionestazionijpamaven.dao.EntityManagerUtil;
import it.prova.gestionestazionijpamaven.dao.stazione.StazioneDao;
import it.prova.gestionestazionijpamaven.model.Stazione;
import it.prova.gestionestazionijpamaven.model.Treno;

public class StazioneServiceImpl implements StazioneService{

	private StazioneDao stazioneDaoInstance;

	@Override
	public List<Stazione> listAllStazioni() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			stazioneDaoInstance.setEntityManager(entityManager);

			return stazioneDaoInstance.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Stazione caricaSingoloStazione(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			stazioneDaoInstance.setEntityManager(entityManager);

			return stazioneDaoInstance.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	
	

	@Override
	public void aggiorna(Stazione stazioneInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			stazioneDaoInstance.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			stazioneDaoInstance.update(stazioneInstance);

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
	public void inserisciNuovo(Stazione stazioneInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			stazioneDaoInstance.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			stazioneDaoInstance.insert(stazioneInstance);

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
	public void rimuovi(Stazione stazioneInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			stazioneDaoInstance.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			stazioneDaoInstance.delete(stazioneInstance);

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
	public void setStazioneDao(StazioneDao stazioneDaoInstance) {
		this.stazioneDaoInstance=stazioneDaoInstance;
	}

	@Override
	public void aggiungiTreno(Treno trenoInstance, Stazione stazioneInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			stazioneDaoInstance.setEntityManager(entityManager);

			trenoInstance = entityManager.merge(trenoInstance);
			stazioneInstance = entityManager.merge(stazioneInstance);
			
			stazioneInstance.addToTreno(trenoInstance);

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
	public void rimuoviTreno(Treno trenoInstance, Stazione stazioneInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			stazioneDaoInstance.setEntityManager(entityManager);

			trenoInstance = entityManager.merge(trenoInstance);
			stazioneInstance = entityManager.merge(stazioneInstance);
			
			stazioneInstance.removeFromTreno(trenoInstance);

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
	public Stazione caricaEagerConTreni(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			stazioneDaoInstance.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return stazioneDaoInstance.findByFetchingTreni(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	

}
