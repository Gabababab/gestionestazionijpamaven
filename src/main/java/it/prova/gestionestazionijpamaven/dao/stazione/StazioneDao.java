package it.prova.gestionestazionijpamaven.dao.stazione;

import it.prova.gestionestazionijpamaven.dao.IBaseDAO;
import it.prova.gestionestazionijpamaven.model.Stazione;

public interface StazioneDao extends IBaseDAO<Stazione>{
	
	public Stazione findByFetchingTreni(Long id);
}
