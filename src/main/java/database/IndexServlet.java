package database;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String btnValue = (String) request.getSession().getAttribute("btn");

		if(btnValue == null || btnValue.equals("loggInn")) {
			request.getRequestDispatcher("WEB-INF/logginn.jsp").forward(request, response);
		}else {
			request.setAttribute("kjonn", "M");
			request.getRequestDispatcher("WEB-INF/paamelding.jsp").forward(request, response);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String btnValue = request.getParameter("btn");
		
		request.getSession().setAttribute("btn", btnValue);
		
		response.sendRedirect("IndexServlet");
	}

}
