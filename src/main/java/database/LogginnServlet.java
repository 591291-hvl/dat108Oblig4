package database;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logginnServlet")
public class LogginnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BrukerDAO brukerDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("deltagerServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mobil = (String) request.getParameter("mobil");
		List<Bruker> brukere = brukerDAO.hentAlleBrukere();

		if (brukere.stream().anyMatch(a -> a.getMobil().equals(mobil))) {
			//Get from user input
			String passordInp = (String) request.getParameter("passord");
			//Get from database
			Bruker sjekkBruker = brukerDAO.hentBruker(mobil);
			String brukerSalt = sjekkBruker.getPassord().getSalt();
			String brukerHash = sjekkBruker.getPassord().getHash();
			if (PassordUtil.validerMedSalt(passordInp, brukerSalt, brukerHash)) {
				//logginn
				int timeout = getServletContext().getSessionTimeout();
				LoggInnUtil.loggInn(request, mobil, timeout);
				response.sendRedirect("logginnServlet");
			}else {
				request.setAttribute("mobilVal", mobil);
				request.setAttribute("feilMelding", "Feil Passord");
				request.getRequestDispatcher("WEB-INF/logginn.jsp")
						.forward(request, response);
			}
			
		}else {
			request.setAttribute("feilMelding", "Mobilnummer er ikke registrert");
			request.getRequestDispatcher("WEB-INF/logginn.jsp")
					.forward(request, response);
		}

	}
}
