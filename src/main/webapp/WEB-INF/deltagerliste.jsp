<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>Deltagerliste</title>
</head>
<body>

	<h2>Deltagerliste</h2>
	<table class="pure-table">
		<tr bgcolor="#cccccc">
			<th>Kjønn</th>
			<th align="left">Navn</th>
			<th align="left">Mobil</th>
		</tr>
		<c:forEach items="${deltagere}" var="d">
			<tr bgcolor="<c:out value="${deltager.equals(d.mobil) ? '#aaffaa' : '#ffffff'}"/>">
				<td align="center">${d.kjonn.equals('Mann') ? '&#9794;' : '&#9792;'}</td>
				<td><c:out value="${d.fornavn}" />
					<c:out value="${d.etternavn}" /></td>
				<td><c:out value="${d.mobil}" /></td>
			</tr>
		</c:forEach>
		
	</table>
	<p>
		<a href="loggutServlet">Ferdig</a>
	</p>
</body>
</html>