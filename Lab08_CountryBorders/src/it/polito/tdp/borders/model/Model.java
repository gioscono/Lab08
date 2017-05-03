package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	UndirectedGraph<Country, DefaultEdge> grafo ;
	List<Country> listaCountry;
	List<Border> listaBorder;
	
	public Model() {}

	public String generaGrafo(int anno) {
		
		grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		listaCountry = new ArrayList<Country>();
		listaBorder = new ArrayList<Border>();
		
		BordersDAO dao = new BordersDAO();
		
		listaCountry = dao.loadAllCountries();
		listaBorder = dao.getCountryPairs(anno);
		
		for(Border b : listaBorder){
			Country c1=ricercaCountry(b.getC1Code());
			Country c2=ricercaCountry(b.getC2Code());
			
			grafo.addVertex(c1);
			grafo.addVertex(c2);
			
			grafo.addEdge(c1, c2);
		}

		System.out.println(grafo);
		
		ConnectivityInspector<Country, DefaultEdge> cv = new ConnectivityInspector<Country, DefaultEdge>(grafo);
		List<Set<Country>> listaSet = cv.connectedSets();
		
		String ris = "";
		for(Country c : grafo.vertexSet()){
			ris += c.getStateName()+" "+grafo.degreeOf(c)+"\n";
		}
		ris+="Numero di componenti connesse: "+listaSet.size();
		return ris;
	}

	
	public Country ricercaCountry(int cod){
		for(Country c : listaCountry){
			if(c.getcCode()==cod)
				return c;
		}
		return null;
	}
}
