package it.prova.gestionestazionijpamaven.test;

import java.util.List;

import it.prova.gestionestazionijpamaven.dao.EntityManagerUtil;
import it.prova.gestionestazionijpamaven.model.Citta;
import it.prova.gestionestazionijpamaven.model.Stazione;
import it.prova.gestionestazionijpamaven.model.Treno;
import it.prova.gestionestazionijpamaven.service.MyServiceFactory;
import it.prova.gestionestazionijpamaven.service.citta.CittaService;
import it.prova.gestionestazionijpamaven.service.stazione.StazioneService;
import it.prova.gestionestazionijpamaven.service.treno.TrenoService;

public class TestGestionestazioni {
	public static void main(String[] args) {

		CittaService cittaServiceInstance = MyServiceFactory.getCittaServiceInstance();
		StazioneService stazioneServiceInstance = MyServiceFactory.getStazioneServiceInstance();
		TrenoService trenoServiceInstance = MyServiceFactory.getTrenoServiceInstance();

		try {

			System.out.println("In tabella Citta ci sono " + cittaServiceInstance.listAllCitta().size() + " elementi.");

			System.out.println(
					"In tabella Stazione ci sono " + stazioneServiceInstance.listAllStazioni().size() + " elementi.");

			System.out.println("In tabella Treno ci sono " + trenoServiceInstance.listAllTreno().size() + " elementi.");

			System.out.println(
					"**************************** inizio batteria di test ********************************************");
			// Test insert

			testInserimentoCitta(cittaServiceInstance);

			testInserimentoStazione(stazioneServiceInstance, cittaServiceInstance);

			testInserimentoTreno(trenoServiceInstance);

			// Test associazioni
			testCollegamentoStazioneCitta(stazioneServiceInstance, cittaServiceInstance);

			testCollegamentoTrenoStazioneCitta(stazioneServiceInstance, cittaServiceInstance, trenoServiceInstance);

			testCancellazioneCittaCollegataAStazioniConTreni(stazioneServiceInstance, cittaServiceInstance,
					trenoServiceInstance);

			testListAllAbitantiByTreno(stazioneServiceInstance, cittaServiceInstance, trenoServiceInstance);

			System.out.println(
					"****************************** fine batteria di test ********************************************");

			System.out.println("In tabella Citta ci sono " + cittaServiceInstance.listAllCitta().size() + " elementi.");

			System.out.println(
					"In tabella Stazione ci sono " + stazioneServiceInstance.listAllStazioni().size() + " elementi.");

			System.out.println("In tabella Treno ci sono " + trenoServiceInstance.listAllTreno().size() + " elementi.");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			EntityManagerUtil.shutdown();
		}
	}

	// Test INSERT

	private static void testInserimentoCitta(CittaService cittaServiceInstance) throws Exception {
		System.out.println(".......testInserimentoCitta inizio.............");

		Citta nuovoCitta = new Citta("Roma", 40);
		cittaServiceInstance.inserisciNuovo(nuovoCitta);
		if (nuovoCitta.getId() == null)
			throw new RuntimeException("testInserimentoCitta fallito ");

		// Flush
		cittaServiceInstance.rimuovi(nuovoCitta);
		System.out.println(".......testInserimentoCitta fine: PASSED.............");
	}

	private static void testInserimentoStazione(StazioneService stazioneServiceInstance,
			CittaService cittaServiceInstance) throws Exception {
		System.out.println(".......testInserimentoStazione inizio.............");

		Citta nuovoCitta = new Citta("Cartagine", 140);
		cittaServiceInstance.inserisciNuovo(nuovoCitta);

		Stazione nuovoStazione = new Stazione("A1", "Via g");
		nuovoStazione.setCitta(nuovoCitta);
		stazioneServiceInstance.inserisciNuovo(nuovoStazione);

		if (nuovoStazione.getId() == null)
			throw new RuntimeException("testInserimentoStazione fallito ");

		// Flush
		stazioneServiceInstance.rimuovi(nuovoStazione);
		cittaServiceInstance.rimuovi(nuovoCitta);
		System.out.println(".......testInserimentoStazione fine: PASSED.............");
	}

	private static void testInserimentoTreno(TrenoService trenoServiceInstance) throws Exception {
		System.out.println(".......testInserimentoTreno inizio.............");

		Treno nuovoTreno = new Treno("regionale", "AF567");
		trenoServiceInstance.inserisciNuovo(nuovoTreno);
		if (nuovoTreno.getId() == null)
			throw new RuntimeException("testInserimentoTreno fallito ");

		// Flush
		trenoServiceInstance.rimuovi(nuovoTreno);
		System.out.println(".......testInserimentoTreno fine: PASSED.............");
	}

	// creazione di città, creazione di due stazioni e collegamento alla città
	// creata
	private static void testCollegamentoStazioneCitta(StazioneService stazioneServiceInstance,
			CittaService cittaServiceInstance) throws Exception {

		System.out.println(".......testCollegamentoStazioneCitta inizio.............");

		Citta nuovoCitta = new Citta("Cartagine", 140);
		cittaServiceInstance.inserisciNuovo(nuovoCitta);

		Stazione stazione1 = new Stazione("A1", "Via g");
		stazione1.setCitta(nuovoCitta);
		stazioneServiceInstance.inserisciNuovo(stazione1);

		Stazione stazione2 = new Stazione("A2", "Via f");
		stazione2.setCitta(nuovoCitta);
		stazioneServiceInstance.inserisciNuovo(stazione2);

		Long idCitta = nuovoCitta.getId();
		Citta cittaReload = cittaServiceInstance.caricaEagerConStazioni(idCitta);
		if (cittaReload.getStazioni().size() != 2)
			throw new RuntimeException("testCollegamentoStazioneCitta fallito ");

		stazioneServiceInstance.rimuovi(stazione1);
		stazioneServiceInstance.rimuovi(stazione2);
		cittaServiceInstance.rimuovi(nuovoCitta);
		System.out.println(".......testCollegamentoStazioneCitta fine: PASSED.............");
	}

	// creazione di città, creazione di una stazione, collegamento a città,
	// creazione di due treni e collegamento alla stazione suddetta;
	private static void testCollegamentoTrenoStazioneCitta(StazioneService stazioneServiceInstance,
			CittaService cittaServiceInstance, TrenoService trenoServiceInstance) throws Exception {
		System.out.println(".......testCollegamentoTrenoStazioneCitta inizio.............");

		Citta nuovoCitta = new Citta("Creta", 140);
		cittaServiceInstance.inserisciNuovo(nuovoCitta);

		Stazione nuovoStazione = new Stazione("Termini", "Via g");
		nuovoStazione.setCitta(nuovoCitta);
		stazioneServiceInstance.inserisciNuovo(nuovoStazione);

		Treno treno1 = new Treno("Regionale", "fg56");
		stazioneServiceInstance.aggiungiTreno(treno1, nuovoStazione);

		Treno treno2 = new Treno("Frecciabianca", "GFH78");
		stazioneServiceInstance.aggiungiTreno(treno2, nuovoStazione);

		Long idStazione = nuovoStazione.getId();
		Stazione stazioneReload = stazioneServiceInstance.caricaEagerConTreni(idStazione);

		if (stazioneReload.getTreni().size() != 2)
			throw new RuntimeException("testCollegamentoTrenoStazioneCitta fallito ");

		// Flush
		stazioneServiceInstance.rimuoviTreno(treno1, stazioneReload);
		stazioneServiceInstance.rimuoviTreno(treno2, stazioneReload);
		trenoServiceInstance.rimuovi(treno1);
		trenoServiceInstance.rimuovi(treno2);
		stazioneServiceInstance.rimuovi(stazioneReload);
		cittaServiceInstance.rimuovi(nuovoCitta);
		System.out.println(".......testCollegamentoTrenoStazioneCitta fine: PASSED.............");
	}

	// creazione di città, creazione di una stazione e collegamento a città,
	// creazione di due treni associati alla stazione, cancellazione della città
	private static void testCancellazioneCittaCollegataAStazioniConTreni(StazioneService stazioneServiceInstance,
			CittaService cittaServiceInstance, TrenoService trenoServiceInstance) throws Exception {
		System.out.println(".......testCancellazioneCittaCollegataAStazioniConTreni inizio.............");

		Citta nuovoCitta = new Citta("Creta", 140);
		cittaServiceInstance.inserisciNuovo(nuovoCitta);

		Stazione nuovoStazione = new Stazione("Termini", "Via g");
		nuovoStazione.setCitta(nuovoCitta);
		stazioneServiceInstance.inserisciNuovo(nuovoStazione);

		Treno treno1 = new Treno("Regionale", "fg56");
		stazioneServiceInstance.aggiungiTreno(treno1, nuovoStazione);

		Treno treno2 = new Treno("Frecciabianca", "GFH78");
		stazioneServiceInstance.aggiungiTreno(treno2, nuovoStazione);

		Long idStazione = nuovoStazione.getId();
		Stazione stazioneReload = stazioneServiceInstance.caricaEagerConTreni(idStazione);

		stazioneServiceInstance.rimuoviTreno(treno1, stazioneReload);
		stazioneServiceInstance.rimuoviTreno(treno2, stazioneReload);
		trenoServiceInstance.rimuovi(treno1);
		trenoServiceInstance.rimuovi(treno2);
		stazioneServiceInstance.rimuovi(stazioneReload);
		cittaServiceInstance.rimuovi(nuovoCitta);

		if (cittaServiceInstance.listAllCitta().size() != 0)
			throw new RuntimeException("testCancellazioneCittaCollegataAStazioniConTreni fallito ");

		System.out.println(".......testCancellazioneCittaCollegataAStazioniConTreni fine: PASSED.............");
	}

	// voglio la lista del numero di abitanti delle città raggiunte da un
	// determinato treno (per questo test creare 4 città, 1 nuova stazione per
	// ciascuna città, un treno per ogni singola stazione)

	private static void testListAllAbitantiByTreno(StazioneService stazioneServiceInstance,
			CittaService cittaServiceInstance, TrenoService trenoServiceInstance) throws Exception {

		System.out.println(".......testListAllAbitantiByTreno inizio.............");

		// Creo Città e stazioni associate
		Citta citta1 = new Citta("Fregene", 10);
		cittaServiceInstance.inserisciNuovo(citta1);

		Stazione stazione1 = new Stazione("Arte", "Via tullio");
		stazione1.setCitta(citta1);
		stazioneServiceInstance.inserisciNuovo(stazione1);

		Citta citta2 = new Citta("Creta", 90);
		cittaServiceInstance.inserisciNuovo(citta2);

		Stazione stazione2 = new Stazione("Termini", "Via g");
		stazione2.setCitta(citta2);
		stazioneServiceInstance.inserisciNuovo(stazione2);

		Citta citta3 = new Citta("Cagliari", 35);
		cittaServiceInstance.inserisciNuovo(citta3);

		Stazione stazione3 = new Stazione("Destra", "Via alta");
		stazione3.setCitta(citta3);
		stazioneServiceInstance.inserisciNuovo(stazione3);

		Citta citta4 = new Citta("Teramo", 135);
		cittaServiceInstance.inserisciNuovo(citta4);

		Stazione stazione4 = new Stazione("Sinistra", "Via bassa");
		stazione4.setCitta(citta4);
		stazioneServiceInstance.inserisciNuovo(stazione4);

		// Creo Treni associati a stazioni
		Treno treno1 = new Treno("Regionale", "fg56");
		stazioneServiceInstance.aggiungiTreno(treno1, stazione1);
		stazioneServiceInstance.aggiungiTreno(treno1, stazione2);
		stazioneServiceInstance.aggiungiTreno(treno1, stazione3);
		stazioneServiceInstance.aggiungiTreno(treno1, stazione4);

		Long idTreno = treno1.getId();
		List<Integer> risultatiRicercaAbitantiByTreno = cittaServiceInstance.ricercaAbitantiByTreno(idTreno);
		
		for(Integer item:risultatiRicercaAbitantiByTreno)
			System.out.println("Numero abitanti"+item);
		
		System.out.println(".......testListAllAbitantiByTreno fine: PASSED.............");
	}
}
