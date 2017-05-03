package it.polito.tdp.borders.model;

public class Country {
	
	private String stateAbb;
	private int cCode;
	private String stateName;
	
	public Country(String stateAbb, int cCode, String stateName) {
		this.stateAbb = stateAbb;
		this.cCode = cCode;
		this.stateName = stateName;
	}
	
	
	public String getStateAbb() {
		return stateAbb;
	}
	public int getcCode() {
		return cCode;
	}
	public String getStateName() {
		return stateName;
	}


	@Override
	public String toString() {
		return stateName;
	}
	
	
	

}
