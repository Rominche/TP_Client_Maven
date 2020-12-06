<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.Ville"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Supression de ville</title>
</head>
<body>
	<div class="card border-0 shadow my-5">
	<h1 class="font-weight-light">Supression de la ville code INSEE : <%Ville ville = (Ville)session.getAttribute("villeToDelete");%>
	<%=ville.getCodeCommune() %></h1>
	</div>
	<br>
	<div> <%=ville.getNomCommune() %></div>
	
	<form accept-charset="UTF-8" role="form" method="post">
	<br> <br>
		<input type="hidden" name="choix" id="chois" value="delete">
		<button type="submit" class="btn btn-primary btn-block" id="submit">
			<span>Supprimer</span>
		</button>
	
	</form>
		<form accept-charset="UTF-8" role="form" method="post">
	<br> <br>
		<input type="hidden" name="choix" id="chois" value="cancel">
		<button type="submit" class="btn btn-primary btn-block" id="submit">
			<span>Annuler</span>
		</button>
	
	</form>
	
</body>
</html>