package it.prova.gestionestazionijpamaven.service.citta;

import java.util.List;

import it.prova.gestionestazionijpamaven.dao.citta.CittaDao;
import it.prova.gestionestazionijpamaven.model.Citta;



public interface CittaService {
	
	public List<Citta> listAllCitta() throws Exception;

	public Citta caricaSingoloCitta(Long id) throws Exception;
	
	public Citta caricaEagerConStazioni(Long id)throws Exception;

	public void aggiorna(Citta cittaInstance) throws Exception;

	public void inserisciNuovo(Citta cittaInstance) throws Exception;

	public void rimuovi(Citta cittaInstance) throws Exception;
	
	public List<Integer> ricercaAbitantiByTreno(Long id)throws Exception;

	public void setCittaDao(CittaDao cittaDaoInstance);
}
