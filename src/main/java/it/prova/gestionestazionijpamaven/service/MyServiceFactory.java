package it.prova.gestionestazionijpamaven.service;

import it.prova.gestionestazionijpamaven.dao.MyDaoFactory;
import it.prova.gestionestazionijpamaven.service.citta.CittaService;
import it.prova.gestionestazionijpamaven.service.citta.CittaServiceImpl;
import it.prova.gestionestazionijpamaven.service.stazione.StazioneService;
import it.prova.gestionestazionijpamaven.service.stazione.StazioneServiceImpl;
import it.prova.gestionestazionijpamaven.service.treno.TrenoService;
import it.prova.gestionestazionijpamaven.service.treno.TrenoServiceImpl;

public class MyServiceFactory {
	
	private static CittaService cittaServiceInstance=null;
	private static StazioneService stazioneServiceInstance=null;
	private static TrenoService trenoServiceInstance=null;
	
	public static CittaService getCittaServiceInstance() {
		if(cittaServiceInstance==null)
			cittaServiceInstance=new CittaServiceImpl();
		
		cittaServiceInstance.setCittaDao(MyDaoFactory.getCittaDaoInstance());
		
		return cittaServiceInstance;
	}
	
	public static StazioneService getStazioneServiceInstance() {
		if(stazioneServiceInstance==null)
			stazioneServiceInstance=new StazioneServiceImpl();
		
		stazioneServiceInstance.setStazioneDao(MyDaoFactory.getStazioneDaoInstance());
		
		return stazioneServiceInstance;
	}
	
	public static TrenoService getTrenoServiceInstance() {
		if(trenoServiceInstance==null)
			trenoServiceInstance=new TrenoServiceImpl();
		
		trenoServiceInstance.setTrenoDao(MyDaoFactory.getTrenoDaoInstance());
		
		return trenoServiceInstance;
	}


}
