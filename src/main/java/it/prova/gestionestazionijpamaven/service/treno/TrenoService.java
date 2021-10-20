package it.prova.gestionestazionijpamaven.service.treno;

import java.util.List;

import it.prova.gestionestazionijpamaven.dao.treno.TrenoDao;
import it.prova.gestionestazionijpamaven.model.Treno;



public interface TrenoService {

	public List<Treno> listAllTreno() throws Exception;

	public Treno caricaSingoloTreno(Long id) throws Exception;

	public void aggiorna(Treno trenoInstance) throws Exception;

	public void inserisciNuovo(Treno trenoInstance) throws Exception;

	public void rimuovi(Treno trenoInstance) throws Exception;

	public void setTrenoDao(TrenoDao trenoDaoInstance);
}
