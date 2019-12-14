package pt.iade.dsm.models;

import java.time.LocalDate;


public class Adoption {
	
   private int AdoptionID; 
   private String guestID; 
   private int  dogID; 
   private int employeeID;  
   private String state; 
   private LocalDate requestDate;
   

   public Adoption(String guestID, int dogID, String state) {
		setGuestID(guestID);
		setDogID(dogID);
		setState(state);
		setRequestDate(requestDate);
	}
   

public int getAdoptionID() {
	return AdoptionID;
}


public void setAdoptionID(int adoptionID) {
	AdoptionID = adoptionID;
}


public String getGuestID() {
	return guestID;
}


public void setGuestID(String guestID) {
	this.guestID = guestID;
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


public String getState() {
	return state;
}


public void setState(String state) {
	this.state = state;
}


public LocalDate getRequestDate() {
	return requestDate;
}


public void setRequestDate(LocalDate requestDate) {
	requestDate = LocalDate.now();
	this.requestDate = requestDate;
}

   
   
}
