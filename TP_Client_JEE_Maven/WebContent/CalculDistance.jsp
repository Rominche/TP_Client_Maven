<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.Ville"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>distance</title>
</head>
<body>
<% Ville ville1 = (Ville)request.getAttribute("ville1");
   Ville ville2 = (Ville)request.getAttribute("ville2");%>
	<h1 class="font-weight-light">Distance entre <%=ville1.getNomCommune() %> et <%=ville2.getNomCommune()%> : <%=session.getAttribute("distance")%> km.</h1>
	<table>
		<a href=Initialisation.jsp>Retour</a>
	</table>
</body>
</html>