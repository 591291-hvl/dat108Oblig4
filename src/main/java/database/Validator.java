package database;

public class Validator {

	public static boolean isGyldigName(String name) {

		if (name == null) {
			return false;
		}
		return name.matches("^[A-ZÆØÅ][a-zæøå]*(([ -])[A-ZÆØÅ][a-zæøå]*)*$");
	}
//^[A-ZÆØÅ][a-zæøå]+(\\s|-)?[A-ZÆØÅ][a-zæøå]+$
//^[A-ZÆØÅ][A-ZÆØÅa-zæøå0-9- ]{3,19}$
	public static boolean isGyldigMobil(String mobil) {
		if (mobil == null) {
			return false;
		}
		return mobil.matches("^\\d{8}$");
	}

	public static boolean isGyldigPassord(String passord) {
		if (passord == null) {
			return false;
		}
		return passord.matches("^(?=.*[0-9])(?=.*[a-zæøå])(?=.*[A-ZÆØÅ])(?=.*[\\W|\\_])(?=\\S+$).{8,}$");
	}
}