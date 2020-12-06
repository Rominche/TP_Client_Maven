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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Servlet implementation class initialisation
 */
@WebServlet("/initialization")
public class InitialisationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitialisationServlet() {
        super();
    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	ArrayList<Ville> villes = new ArrayList<Ville>();
    	villes = getApiList(request, response);

    	session.setAttribute("villes", villes);
		this.getServletContext().getRequestDispatcher("/Initialisation.jsp").forward(request, response);
    }
    
	private ArrayList<Ville> getApiList(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Ville> villes = new ArrayList<Ville>();
		request.setAttribute("listeDes50", null);
		
		@SuppressWarnings("rawtypes")
		HttpResponse apiResponse;
		try {
			apiResponse = Unirest.get("http://127.0.0.1:8181/ville").asJson();
			JsonArray jArray = JsonParser.parseString(apiResponse.getBody().toString()).getAsJsonArray();
			villes = this.json2List(jArray);
		} catch (UnirestException err) {
			err.printStackTrace();
		}
		return villes;
	}
	
	private ArrayList<Ville> json2List(JsonArray jArray) {
		ArrayList<Ville> villes = new ArrayList<Ville>();
		final Gson gson = new GsonBuilder().create();
		for (JsonElement json : jArray) {
			villes.add(gson.fromJson(json, Ville.class));
		}
		return villes;
	}
	

}
