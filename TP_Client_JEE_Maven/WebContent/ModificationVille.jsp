<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.Ville"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier</title>
</head>
<body>
	<div class="card border-0 shadow my-5">
	<h1 class="font-weight-light">Modifier les données de : <%Ville ville = (Ville)session.getAttribute("villetoModify");%>
	<%=ville.getNomCommune()%></h1>
		
		
	<form accept-charset="UTF-8" role="form" method="post">
		<br> <br>
		<label>Code commune INSEE : <%=ville.getCodeCommune() %></label>
			<br> <br>
			<div class="form-group">
				<label for="nomCommune">Nom de la commune : </label>
				<input class="form-control" placeholder="nomCommune" name="nomCommune" value=<%=ville.getNomCommune() %>>
			</div> <br>
			<div class="form-group">
				<label for="nomCommune">Code postal : </label>
				<input class="form-control" placeholder="codePostal" name="codePostal" value=<%=ville.getCodePostal() %>>
			</div> <br>
			<div class="form-group">
				<label for="nomCommune">Libellé acheminement : </label>
				<input class="form-control" placeholder="libelleAcheminement" name="libelleAcheminement" value=<%= ville.getLibelleAcheminement()%>>
			</div> <br>
			<div class="form-group">
				<label for="nomCommune">Ligne : </label>
				<input class="form-control" placeholder="Ligne" name="ligne" value=<%=ville.getLigne() %>>
			</div> <br>
			<div class="form-group">
				<label for="nomCommune">Latitude : </label>
				<input class="form-control" placeholder="Latitude" name="latitude" value=<%=ville.getLatitude() %>>
			</div> <br>
			<div class="form-group">
				<label for="nomCommune">Longitude : </label>
				<input class="form-control" placeholder="Longitude" name="longitude" value=<%=ville.getLongitude() %>>
			</div>
			
			<br> <br>
			
			<input type="hidden" name="actionModif" id="actionModif" value="save" />
												
			<button type="submit" class="btn btn-primary btn-block" id="submit">
				<span>Enregistrer</span>
			</button>
		
		</form>
	</div>
</body>
</html>