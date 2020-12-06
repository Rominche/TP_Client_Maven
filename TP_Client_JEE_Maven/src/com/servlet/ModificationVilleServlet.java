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
 * Servlet implementation class ModifficationVilleServlet
 */
@WebServlet("/Modification")
public class ModificationVilleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String VILLE_MODIFIEE = "villeModifiee";
	public static final String VUE_FORM = "/ModificationVille.jsp";
	public static final String ATT_ACTION = "actionModif";
	
	private String ville = null;
	ArrayList<Ville> villes = new ArrayList<Ville>();
	Ville villeToChange = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificationVilleServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		villes = (ArrayList<Ville>) session.getAttribute("villes");
		ville = request.getParameter("ville");
		findVille(ville, villes);
		session.setAttribute("villetoModify", villeToChange);
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionRequired = request.getParameter(ATT_ACTION);
		
		switch(actionRequired) {
		case "save" :
			saveModif(request, response);
			break;
		case "cancel" :
			this.getServletContext().getRequestDispatcher("/VilleAffichage.jsp").forward(request, response);
			break;
		default :
			doGet(request, response);
			break;
		}
	}
	
	private void findVille(String ville, ArrayList<Ville> villes) {
		for (Ville villeOff : villes) {
			if(villeOff.getCodeCommune().equals(ville)) {
				villeToChange = villeOff;
			}
		}
	}
	
	private void saveModif(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String codeCommune = villeToChange.getCodeCommune();
		String nomCommune = (String) request.getParameter("nomCommune");
		String codePostal = (String) request.getParameter("codePostal");
		String libelleAcheminement = (String) request.getParameter("libelleAcheminement");
		String ligne = (String) request.getParameter("ligne");
		String longitude = (String) request.getParameter("longitude");
		String latitude = (String) request.getParameter("latitude");

		Ville ville = new Ville();
		ville.setCodeCommune(codeCommune);
		ville.setNomCommune(nomCommune);
		ville.setCodePostal(codePostal);
		ville.setLibelleAcheminement(libelleAcheminement);
		if (ligne == null) {
			ville.setLigne("");
		} else {
			ville.setLigne(ligne);
		}
		ville.setLongitude(longitude);
		ville.setLatitude(latitude);
		
		Gson gson = new Gson();
		String villeJSon = gson.toJson(ville);

		String url = "http://localhost:8181/ville/modify/" + ville.getCodeCommune();
		try {
			(Unirest.post(url).header("Content-type", "application/json")).body(villeJSon).asString();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		RequestDispatcher req = request.getRequestDispatcher("ModificationValide.jsp");
		req.forward(request, response);
	}

}
