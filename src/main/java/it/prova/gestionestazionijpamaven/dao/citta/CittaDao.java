package it.prova.gestionestazionijpamaven.dao.citta;

import java.util.List;

import it.prova.gestionestazionijpamaven.dao.IBaseDAO;
import it.prova.gestionestazionijpamaven.model.Citta;

public interface CittaDao extends IBaseDAO<Citta>{
	
	public Citta findByFetchingStazioni(Long id);
	
	public List<Integer> findAllAbitantiByTreno(Long id);
}
