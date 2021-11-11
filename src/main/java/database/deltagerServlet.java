package database;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deltagerServlet")
public class deltagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private BrukerDAO brukerDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(!LoggInnUtil.erInnlogget(request)) {
			request.getRequestDispatcher("WEB-INF/logginn.jsp").forward(request, response);
			return;
		}

		// Databehandling her!
		List<Bruker> deltagere = brukerDAO.hentAlleBrukere();	
		
		//Idea from radix sort
		deltagere = deltagere.stream().sorted(Comparator.comparing(Bruker::getEtternavn)).collect(Collectors.toList());
		deltagere = deltagere.stream().sorted(Comparator.comparing(Bruker::getFornavn)).collect(Collectors.toList());
		
		// Ta vare på i requesten til JSP-en
		
		request.setAttribute("deltagere", deltagere);
		request.setAttribute("deltager", LoggInnUtil.hvemInnlogget(request));

		// Gjør et forward-kall internt på serveren til JSP-side for visning
		request.getRequestDispatcher("WEB-INF/deltagerliste.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(!LoggInnUtil.erInnlogget(request)) {
			request.getRequestDispatcher("WEB-INF/logginn.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("deltagerServlet");
	}

}
