package database;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BrukerDAO {
	
	@PersistenceContext(name = "brukerDB")
    private EntityManager em;
	
	public synchronized List<Bruker> hentAlleBrukere() {
		return em.createQuery("SELECT b FROM Bruker b", Bruker.class).getResultList();
	}
	
	
	public synchronized Bruker hentBruker(String navn) {
		return em.find(Bruker.class, navn);
	}
	
	//true if user exist
	public synchronized boolean sjekkBruker(String navn) {
		return hentBruker(navn) == null;
	}

	public synchronized void lagreNyBruker(Bruker nyBruker) {
		em.persist(nyBruker);
	}
}


