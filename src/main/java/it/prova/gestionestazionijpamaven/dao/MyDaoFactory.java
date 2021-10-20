package it.prova.gestionestazionijpamaven.dao;

import it.prova.gestionestazionijpamaven.dao.citta.CittaDao;
import it.prova.gestionestazionijpamaven.dao.citta.CittaDaoImpl;
import it.prova.gestionestazionijpamaven.dao.stazione.StazioneDao;
import it.prova.gestionestazionijpamaven.dao.stazione.StazioneDaoImpl;
import it.prova.gestionestazionijpamaven.dao.treno.TrenoDao;
import it.prova.gestionestazionijpamaven.dao.treno.TrenoDaoImpl;

public class MyDaoFactory {
	
	private static CittaDao cittaDaoInstance=null;
	private static StazioneDao stazioneDaoInstance=null;
	private static TrenoDao trenoDaoInstance=null;
	
	public static CittaDao getCittaDaoInstance() {
		if(cittaDaoInstance==null)
			cittaDaoInstance=new CittaDaoImpl();
		
		return cittaDaoInstance;
	}
	
	public static StazioneDao getStazioneDaoInstance() {
		if(stazioneDaoInstance==null)
			stazioneDaoInstance=new StazioneDaoImpl();
		
		return stazioneDaoInstance;
	}
	
	public static TrenoDao getTrenoDaoInstance() {
		if(trenoDaoInstance==null)
			trenoDaoInstance=new TrenoDaoImpl();
		
		return trenoDaoInstance;
	}

}
