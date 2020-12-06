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

/**
 * Servlet implementation class CalculDeDistanceServlet
 */
@WebServlet("/CalculDeDistanceServlet")
public class CalculDeDistanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Ville> villesPage = new ArrayList<Ville>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculDeDistanceServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/Initialisation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ArrayList<Ville> villes = (ArrayList<Ville>) session.getAttribute("villes");
		request.setAttribute("listeDes50", this.firstPage(villes));
		
		String ville1 = request.getParameter("ville1");
		String ville2 = request.getParameter("ville2");
		
		String actionDemandee = request.getParameter("action");
		
		Ville villeCal1 = null;
		Ville villeCal2 = null; 
		for(Ville ville : villes) {
			if (ville.getNomCommune().equals(ville1)) {
				villeCal1 = ville;
				request.setAttribute("ville1", villeCal1);
			}
			if (ville.getNomCommune().equals(ville2)) {
				villeCal2 = ville;
				request.setAttribute("ville2", villeCal2);
			}
		}
		
		switch (actionDemandee) {
		case "calculer" :
			calculDistance(villeCal1, villeCal2, session, request, response);
		}
		doGet(request, response);
	}
	
	private void calculDistance(Ville ville1, Ville ville2, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double long1 = Math.toRadians(Double.parseDouble(ville1.getLatitude()));
		double long2 = Math.toRadians(Double.parseDouble(ville2.getLatitude()));
		double lat1 = Math.toRadians(Double.parseDouble(ville1.getLongitude()));
		double lat2 = Math.toRadians(Double.parseDouble(ville2.getLongitude()));
		
		double distance = 6371*(2 * 
					Math.atan(Math.sqrt(Math.pow(Math.sin((lat2 - lat1)/2), 2) + 
					Math.cos(lat1) * 
					Math.cos(lat2) * Math.pow(Math.sin((long2 - long1)/2), 2))/Math.sqrt(
							1 - Math.pow(Math.sin((lat2 - lat1)/2), 2) + 
							Math.cos(lat1) * 
							Math.cos(lat2) * Math.pow(Math.sin((long2 - long1)/2), 2)
					)));
		
		session.setAttribute("distance", distance);
		RequestDispatcher req = request.getRequestDispatcher("CalculDistance.jsp");
		req.forward(request, response);
	}

	private ArrayList<Ville> firstPage(ArrayList<Ville> allVilles) {
		this.villesPage.clear();
		for (int i = 0; i < 50; i++) {
			if (i < allVilles.size()) {
				this.villesPage.add(allVilles.get(i));
			}
		}
		return this.villesPage;
	}

}
