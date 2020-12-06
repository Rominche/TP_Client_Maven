<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="java.util.ArrayList"%>
<%@ page import="com.Ville"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calcul des distances</title>
</head>
<body>
	<table>
	<tr>
		<td>
			<a href="display">Liste des Villes</a>
			<br>
		</td>
	</tr>
	<tr>
		<td>Calculer la distance entre 2 villes : </td> <br> <br>
		<td>
		<div>
			<form accept-charset="UTF-8" role="form" method="post" action="CalculDeDistanceServlet">
			<div>
				<SELECT name="ville1">
					<%
					@SuppressWarnings("unchecked")
					ArrayList<Ville> ville1 = (ArrayList<Ville>) session.getAttribute("villes");
					for (Ville ville : ville1){
					%>
					<option>
						<%=ville.getNomCommune() %>
					<%
					}
					%>
				</SELECT>
				<SELECT name="ville2">
					<%
					@SuppressWarnings("unchecked")
					ArrayList<Ville> ville2 = (ArrayList<Ville>) session.getAttribute("villes");
					for (Ville ville : ville2){
					%>
					<option>
						<%=ville.getNomCommune() %>
					<%
					}
					%>
				</SELECT>
				<input type="hidden" name="action" id="action" value="calculer">
				<button type="submit" class="btn btn-primary btn-block" id="submit">
					<span>Calculer</span>
				</button>
				</div>
			</form>
		</div>
		</td>
	</tr>
	</table>

</body>
</html>