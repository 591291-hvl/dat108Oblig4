package database;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bruker",schema = "d108Oblig4")
public class Bruker {

    
    private String fornavn;
    private String etternavn;
    @Embedded
    private Passord passord;
    @Id
    private String mobil;
    private String kjonn;
    
	public Bruker(String fornavn, String etternavn, Passord passord, String mobil, String kjonn) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.passord = passord;
		this.mobil = mobil;
		this.kjonn = kjonn;
	}
	
	public Bruker() {}
	
	public String getFornavn() {
		return fornavn;
	}
	
	public String getEtternavn() {
		return etternavn;
	}
	
	public String getMobil() {
		return mobil;
	}
	
	public String getKjonn() {
		return kjonn.equals("M") ? "Mann" : "Kvinne";
	}
	
	
	public Passord getPassord() {
		return passord;
	}
	

	@Override
	public String toString() {
		return fornavn + "\n" + etternavn + "\n" + mobil + "\n" + (kjonn.equals("M") ? "Mann" : "Kvinne");
	}
}
