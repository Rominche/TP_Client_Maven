package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Ville;
import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Servlet implementation class SupressionVilleServlet
 */
@WebServlet("/SupressionVilleServlet")
public class SupressionVilleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_ACTION = "choix";
	private String ville;
	private Ville villeToDelete;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupressionVilleServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ville = (String) request.getParameter("ville");
		HttpSession session = request.getSession();
		ArrayList<Ville> villes = (ArrayList<Ville>) session.getAttribute("villes");
		
		findVille(ville, villes);
		
		session.setAttribute("villeToDelete", villeToDelete);
		
		this.getServletContext().getRequestDispatcher("/SupressionVille.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionDemandee = request.getParameter(ATT_ACTION);

		
		switch(actionDemandee) {
		case "delete" :
			supprimerVille(request, response);
			break;
		case "cancel" :
			this.getServletContext().getRequestDispatcher("/Initialisation.jsp").forward(request, response);
			break;
		default :
			break;
		};
	}
	
	private void findVille(String ville, ArrayList<Ville> villes) {
		for (Ville villeOff : villes) {
			if(villeOff.getCodeCommune().equals(ville)) {
				villeToDelete = villeOff;
			}
		}
	}

	private void supprimerVille(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String url = "http://localhost:8181/ville/delete?codeCommune=" + villeToDelete.getCodeCommune();
		try {
			Unirest.get(url).asString();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/Initialisation.jsp").forward(request, response);
	}
	
	
}
