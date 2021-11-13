<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="module" src="js/registrer.js"></script>
<meta charset="utf-8">
<title>Påmelding</title>
<style>
* {
	padding: 5px;
}

input+span {
	position: relative;
}

input+span::before {
	position: absolute;
	/*right: -20px;*/
	top: 5px;
}

input:invalid {
	border: 2px solid red;
}

input:valid {
	border: 2px solid green;
}

input:invalid+span::before {
	content: '✖';
	color: red;
}

input:valid+span::before {
	content: '✓';
	color: green;
}

input[type="password"].mediumPassword {
	border-color: yellow;
}

input[type="password"].mediumPassword+span::before {
	content: '';
	color: yellow;
}
</style>
</head>
<body>
	<h2>Påmelding</h2>
	<form action="PaameldingServlet" method="post">
		<fieldset>
			<div>
				<label for="fornavn">Fornavn:</label> <input required type="text"
					name="fornavn" pattern="^[A-ZÆØÅ][A-ZÆØÅa-zæøå0-9- ]{3,19}$"
					title="Fornavn må starte med en STOR bokstav, så små"
					value="<c:out value = "${fornavn}"/>" /><span></span> <font
					color="red"><c:out value="${feilFornavn}" /></font>
			</div>
			<div>
				<label for="etternavn">Etternavn:</label> <input required
					type="text" name="etternavn"
					pattern="^[A-ZÆØÅ][A-ZÆØÅa-zæøå0-9- ]{3,19}$"
					title="Etternavn må starte med en STOR bokstav, så små"
					value="<c:out value = "${etternavn}"/>" /><span></span> <font
					color="red"><c:out value="${feilEtternavn}" /></font>
			</div>
			<div>
				<label for="mobil">Mobil (8 siffer):</label> <input required
					type="number" name="mobil" min="10000000" max="99999999"
					title="MobilNummer må ha 8 siffre"
					value="<c:out value = "${mobil}"/>" /><span></span> <font
					color="red"><c:out value="${feilMobil}" /></font>
			</div>
			<div>
				<label for="password">Passord:</label> <input id="pass1" required
					type="password" name="passord"
					title="Passord må innholde stor bokstav, liten bokstav, tall, spesial tegn og være lengre enn 8"
					value="<c:out value = "${passord1}"/>" /><span></span> <font
					color="red"><c:out value="${feilPassord1}" /></font>
			</div>
			<div>
				<label for="passordRepetert">Passord repetert:</label> <input
					id="pass2" required type="password" name="passordRepetert" 
					title="Passordet må være likt det over"
					value="<c:out value = "${passord2}"/>" /><span></span> <font
					color="red"><c:out value="${feilPassord2}" /></font>
			</div>
			<div>
				<label for="kjonn">Kjønn:</label> <input type="radio" name="kjonn"
					value="M" ${kjonn.equals("M") ? 'checked' : ''} />mann <input
					type="radio" name="kjonn" value="K"
					${kjonn.equals("M") ? '' : 'checked'} />kvinne

			</div>
			<div>
				<button type="submit">Meld meg på</button>
			</div>
		</fieldset>
	</form>
</body>
</html>