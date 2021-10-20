package it.prova.gestionestazionijpamaven.service.stazione;

import java.util.List;


import it.prova.gestionestazionijpamaven.dao.stazione.StazioneDao;
import it.prova.gestionestazionijpamaven.model.Stazione;
import it.prova.gestionestazionijpamaven.model.Treno;



public interface StazioneService {
	
	public List<Stazione> listAllStazioni() throws Exception;

	public Stazione caricaSingoloStazione(Long id) throws Exception;
	
	public Stazione caricaEagerConTreni(Long id) throws Exception;

	public void aggiorna(Stazione stazioneInstance) throws Exception;

	public void inserisciNuovo(Stazione stazioneInstance) throws Exception;

	public void rimuovi(Stazione stazioneInstance) throws Exception;
	
	public void aggiungiTreno(Treno trenoInstance, Stazione stazioneInstance)throws Exception;
	
	public void rimuoviTreno(Treno trenoInstance, Stazione stazioneInstance)throws Exception;

	public void setStazioneDao(StazioneDao stazioneDaoInstance);
}
