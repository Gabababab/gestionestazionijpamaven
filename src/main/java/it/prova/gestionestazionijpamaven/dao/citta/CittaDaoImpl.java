package it.prova.gestionestazionijpamaven.dao.citta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestionestazionijpamaven.model.Citta;

public class CittaDaoImpl implements CittaDao{

	private EntityManager entityManager;
	
	@Override
	public List<Citta> list() throws Exception {
		return entityManager.createQuery("from Citta", Citta.class).getResultList();
	}

	@Override
	public Citta get(Long id) throws Exception {
		return entityManager.find(Citta.class, id);
	}

	@Override
	public void update(Citta input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Citta input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Citta input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;
	}

	@Override
	public Citta findByFetchingStazioni(Long id) {
		TypedQuery<Citta> query=entityManager.createQuery("Select c FROM Citta c join fetch c.stazioni s WHERE c.id= :idCitta", Citta.class);
		query.setParameter("idCitta", id);
		return query.getResultList().stream().findFirst().orElse(null);		
	}

	@Override
	public List<Integer> findAllAbitantiByTreno(Long id) {
		TypedQuery<Integer> query=entityManager.createQuery("Select c.numeroAbitanti FROM Citta c join c.stazioni s join s.treni t WHERE t.id= :idTreno", Integer.class);
		query.setParameter("idTreno", id);
		return query.getResultList();
	}

}
