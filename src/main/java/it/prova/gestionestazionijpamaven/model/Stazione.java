package it.prova.gestionestazionijpamaven.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "stazione")
public class Stazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "denominazione")
	private String denominazione;
	@Column(name = "indirizzo")
	private String indirizzo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "citta_id", nullable = false)
	private Citta citta;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "stazione_treno", joinColumns = @JoinColumn(name = "stazione_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "treno_id", referencedColumnName = "ID"))
	private Set<Treno> treni = new HashSet<Treno>();

	public Stazione() {
	};

	public Stazione(String denominazione, String indirizzo) {
		super();
		this.denominazione = denominazione;
		this.indirizzo = indirizzo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Citta getCitta() {
		return citta;
	}

	public void setCitta(Citta citta) {
		this.citta = citta;
	}

	public Set<Treno> getTreni() {
		return treni;
	}

	public void setTreni(Set<Treno> treni) {
		this.treni = treni;
	}
	
	public void addToTreno(Treno trenoInstance) {
		this.treni.add(trenoInstance);
		trenoInstance.getStazioni().add(this);
	}
	
	public void removeFromTreno(Treno trenoInstance) {
		this.treni.remove(trenoInstance);
		trenoInstance.getStazioni().remove(this);
	}

}
