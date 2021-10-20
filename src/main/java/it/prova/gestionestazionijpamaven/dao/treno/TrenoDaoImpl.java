package it.prova.gestionestazionijpamaven.dao.treno;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionestazionijpamaven.model.Treno;

public class TrenoDaoImpl implements TrenoDao{

	private EntityManager entityManager;
	@Override
	public List<Treno> list() throws Exception {
		return entityManager.createQuery("from Treno", Treno.class).getResultList();
	}

	@Override
	public Treno get(Long id) throws Exception {
		return entityManager.find(Treno.class, id);
	}

	@Override
	public void update(Treno input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Treno input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Treno input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;
	}

}
