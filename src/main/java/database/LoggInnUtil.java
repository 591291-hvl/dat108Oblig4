package database;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoggInnUtil {
	
	//Logge inn
	public static void loggInn(HttpServletRequest request, String username, int timeout) {
		
		//Logge ut
		loggUt(request);
		
		//Logg inn
		HttpSession sesjon = request.getSession(true);
		sesjon.setMaxInactiveInterval(timeout);
		sesjon.setAttribute("brukernavn", username);
		
	}

	//Sjekke om request tilhører innlogget bruker
	
	public static boolean erInnlogget(HttpServletRequest request) {
		HttpSession sesjon = request.getSession(false);
		return sesjon != null && sesjon.getAttribute("brukernavn") != null;
	}
	
	//Logge ut
	public static void loggUt(HttpServletRequest request) {
		HttpSession sesjon = request.getSession(false);
		if (sesjon != null) {
			sesjon.invalidate();
		}
	}
	
	//Sjekk hvem som er innlogget
	public static String hvemInnlogget(HttpServletRequest request) {
		HttpSession sesjon = request.getSession(false);
		return erInnlogget(request) ? (String) sesjon.getAttribute("brukernavn") : null;
	}
	
	
	//Annet ...?

}