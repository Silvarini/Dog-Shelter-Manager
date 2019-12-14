package pt.iade.dsm.models;

import java.time.LocalDate;

public class State {

	
	private String state;
	private int dogID;
	private int employeeID;
	private LocalDate initialDate;
	private int id;
	
	
	public State(String state, int dogID, int employeeID) {
		setState(state);
		setDogID(dogID);
		setEmployeeID(employeeID);
		setInitialDate(initialDate);
	}
	
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getDogID() {
		return dogID;
	}
	public void setDogID(int dogID) {
		
		this.dogID = dogID;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public LocalDate getInitialDate() {
		return initialDate;
	}
	public void setInitialDate(LocalDate initialDate) {
		initialDate = LocalDate.now();
		this.initialDate = initialDate;
		
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
