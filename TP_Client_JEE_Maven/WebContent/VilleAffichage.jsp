<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.Ville"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="card border-0 shadow my-5">
			<div class="card-body p-5">
				<h1 class="font-weight-light">Liste des villes :</h1>
				<br>
				<div style="height: 2500px">
					<table>
					<tr>
						<td>Nom de commune</td>
						<td>Code Postal</td>
						<td>Libellé</td>
						<td>Ligne</td>
						<td>Latitude</td>
						<td>Longitude</td>
					</tr>
					<%
					@SuppressWarnings("unchecked")
					// retrieve your list from the request, with casting 
					ArrayList<Ville> list = (ArrayList<Ville>) request.getAttribute("listeDes50");

					// print the information about every category of the list
					for(Ville ville : list) {
						%>
						<tr>
							<td><%=ville.getNomCommune()%></td>
							<td><%=ville.getCodePostal()%></td>
							<td><%=ville.getLibelleAcheminement() %></td>
							<td><%=ville.getLigne() %></td>
							<td><%=ville.getLatitude() %></td>
							<td><%=ville.getLongitude() %></td>
							<td> 
								<a href="Modification?ville=<%=ville.getCodeCommune()%>">Modifier</a>
							</td>
							<td>       </td>	
							<td>
								<a href="SupressionVilleServlet?ville=<%=ville.getCodeCommune() %>">Supprimer</a>
							</td>
						</tr>
						
					<%
						}
					%>
					<tr></tr>
					<tr>
						<td>
							<form  accept-charset="UTF-8" role="form" method="post">
								<input type="hidden" name="action" id="actionModif" value="previousPage" />
																	
								<button type="submit" class="btn btn-primary btn-block" id="submit">
									<span>Page précédente</span>
								</button>
							</form>
						</td>
						<td></td><td></td><td><%=request.getAttribute("nbPages") %></td><td></td><td></td><td></td><td></td>
						<td>
							<form  accept-charset="UTF-8" role="form" method="post">
								<input type="hidden" name="action" id="actionModif" value="nextPage" />
																	
								<button type="submit" class="btn btn-primary btn-block" id="submit">
									<span>Page suivante</span>
								</button>
							</form>
						</td>
					</tr>
					<tr></tr>
					<tr>
						<td></td><td></td><td></td><td><a href="Initialisation.jsp">Retour à l'accueil</a></td>
					</tr>
					</table>
				</div>
			</div>
	</div>
</body>
</html>