package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Ville;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Servlet implementation class VilleAffichageServlet
 */
@WebServlet("/display")
public class VilleAffichageServlet extends HttpServlet {

    /**
     * Default constructor. 
     */
    public VilleAffichageServlet() {
    }

	
	private static final long serialVersionUID = 1L;
	private static final String ATT_NEXT_PAGE = "nextPage";
	private static final String ATT_PREVIOUS_PAGE = "previousPage";
	private static final String ATT_LISTE_DE_50 = "listeDes50";
	private static final String NUMERO_PAGE = "numeroPage";
	private static final String VUE_FORM = "/VilleAffichage.jsp";
	private static final String VUE_MODIF = "/ModificationVille.jsp";
	private static final String VUE_DELETE = "/SupressionVille.jsp";
	private static final String ATT_ACTION = "action";

	
	private int indice = 0;
	private ArrayList<Ville> villeBy50 = new ArrayList<Ville>();
	ArrayList<Ville> listeVilles = new ArrayList<Ville>(); 

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getApiList();
		if(villeBy50.size()==0) {
			nextPage(req, resp, listeVilles, indice);
		}
		int nombrePage = this.listeVilles.size()/50;
		req.setAttribute("nbPages", nombrePage);
		// charge une nouvelle liste de 50 dans la page
		req.setAttribute(ATT_LISTE_DE_50, this.villeBy50);
		// met à jour le numéro de la page
		req.setAttribute(NUMERO_PAGE, (this.indice+50)%50);
		//affichage JSP 
		//resp.getWriter().append("Served at: ").append(req.getContextPath());
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(req, resp);
		 	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String actionRequired = request.getParameter(ATT_ACTION);
		getApiList();
		
		switch (actionRequired) {
		case ATT_NEXT_PAGE :
			this.villeBy50.clear();
			nextPage(request, response, this.listeVilles, this.indice);
			doGet(request, response);
			break;
		case ATT_PREVIOUS_PAGE :
			this.villeBy50.clear();
			previousPage(request, response, this.listeVilles, this.indice);
			doGet(request, response);
		default :
			break;
		}
	}
	

	//aficche les villes de la page suivante 
	private void nextPage(HttpServletRequest req, HttpServletResponse resp, ArrayList<Ville> listeVilles, int indice ) throws ServletException, IOException {
		this.indice += 50;
		for (int i = indice; i < indice + 50; i++) {
			if (i < listeVilles.size()) {
				this.villeBy50.add(listeVilles.get(i));
			}
		}
	}
	
	//afficher les villes de la page précédente
	private void previousPage(HttpServletRequest req, HttpServletResponse resp, ArrayList<Ville> listeVilles, int indice ) throws ServletException, IOException {
		this.indice -= 50;
		if(indice < 50) {
			indice = 50;
			this.indice = 50;
		}
		for (int i = indice-50; i < indice; i++) {
			villeBy50.add(listeVilles.get(i));
		}
	}
	
	private void getApiList() {
		@SuppressWarnings("rawtypes")
		HttpResponse response;
		try {
			response = Unirest.get("http://127.0.0.1:8181/ville").asJson();
			JsonArray jArray = JsonParser.parseString(response.getBody().toString()).getAsJsonArray();
			this.listeVilles = this.json2List(jArray);
		} catch (UnirestException err) {
			err.printStackTrace();
		}
	}
	
	private ArrayList<Ville> json2List(JsonArray jArray) {
		ArrayList<Ville> villes = new ArrayList<Ville>();
		final Gson gson = new GsonBuilder().create();
		for (JsonElement json : jArray) {
			villes.add(gson.fromJson(json, Ville.class));
		}
		return villes;
	}
	
	private void editTown(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_MODIF).forward(req, resp);
	}
	
	private void deleteTown(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_DELETE).forward(req, resp);		
	}

}
