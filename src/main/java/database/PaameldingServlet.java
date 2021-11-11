package database;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PaameldingServlet")
public class PaameldingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private BrukerDAO brukerDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		Bruker bruker = (Bruker) request.getSession().getAttribute("bruker");
		
		request.setAttribute("bruker", bruker);
		request.getRequestDispatcher("WEB-INF/paameldingBekreftelse.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// input get
		String fornavn = request.getParameter("fornavn");
		String etternavn = request.getParameter("etternavn");
		String mobil = request.getParameter("mobil");
		String passord = request.getParameter("passord");
		String passordRepetert = request.getParameter("passordRepetert");
		String kjonn = request.getParameter("kjonn");
		// boolean check for if all inputs are valid
		boolean gyldig = true;

		// if check for each input

		// fornavn
		if (!Validator.isGyldigName(fornavn)) {
			gyldig = false;
			request.setAttribute("feilFornavn", "Ugyldig fornavn");

		} else {
			// Retain user input
			request.setAttribute("fornavn", fornavn);
		}

		// etternavn
		if (!Validator.isGyldigName(etternavn)) {
			gyldig = false;
			request.setAttribute("feilEtternavn", "Ugyldig etternavn");

		} else {
			// Retain user input
			request.setAttribute("etternavn", etternavn);
		}

		// mobil
		if (!Validator.isGyldigMobil(mobil) ) {
			//pk, sjekk om den finnes i database
			gyldig = false;
			request.setAttribute("feilMobil", "Ugyldig mobil");

		} else {
			if(!brukerDAO.sjekkBruker(mobil)) {
				gyldig = false;
				request.setAttribute("feilMobil", "Mobil allerede registrert");
			}else {
				// Retain user input
				request.setAttribute("mobil", mobil);
			}
			
		}

		
		// Check if passwords are equal + validate
		if (!Validator.isGyldigPassord(passord) || !passord.equals(passordRepetert)) {
			gyldig = false;
			System.out.println(Validator.isGyldigPassord(passord));
			System.out.println(passord);
			System.out.println(passordRepetert);
			request.setAttribute("feilPassord1", "Ugyldig passord");
			request.setAttribute("feilPassord2", "Passordene må være like");

		} else {
			// Retain user input
			request.setAttribute("passord1", passord);
			request.setAttribute("passord2", passordRepetert);
		}

		// Every input is valid, proceed with user registration
		if (gyldig) {
			// Add user to database
			Passord newpassord = Passord.lagPassord(passord);
			Bruker bruker = new Bruker(fornavn, etternavn, newpassord, mobil, kjonn);
			brukerDAO.lagreNyBruker(bruker);
			// Logg inn user
			int timeout = getServletContext().getSessionTimeout();
			LoggInnUtil.loggInn(request, mobil, timeout);
			// redirect to "Påmeldingsbekreftelse"
			request.getSession().setAttribute("bruker", bruker);
			response.sendRedirect("PaameldingServlet");
		} else {
			// Store all inputs to be displayed in inputfield
			// return to registration page
			
			request.setAttribute("kjonn", kjonn);
			
			request.getRequestDispatcher("WEB-INF/paamelding.jsp").forward(request, response);
		}

	}

}
