package pt.iade.dsm.models;

import java.time.LocalDate;


// TODO: Auto-generated Javadoc
/**
 * The Class Adoption.
 */
public class Adoption {
	
   /** The Adoption ID. */
   private int AdoptionID; 
   
   /** The guest ID. */
   private String guestID; 
   
   /** The dog ID. */
   private int  dogID; 
   
   /** The employee ID. */
   private int employeeID;  
   
   /** The state. */
   private String state; 
   
   /** The request date. */
   private LocalDate requestDate;
   

   /**
    * Instantiates a new adoption.
    *
    * @param guestID the guest ID
    * @param dogID the dog ID
    * @param state the state
    */
   public Adoption(String guestID, int dogID, String state) {
		setGuestID(guestID);
		setDogID(dogID);
		setState(state);
		setRequestDate(requestDate);
	}
   

/**
 * Gets the adoption ID.
 *
 * @return the adoption ID
 */
public int getAdoptionID() {
	return AdoptionID;
}


/**
 * Sets the adoption ID.
 *
 * @param adoptionID the new adoption ID
 */
public void setAdoptionID(int adoptionID) {
	AdoptionID = adoptionID;
}


/**
 * Gets the guest ID.
 *
 * @return the guest ID
 */
public String getGuestID() {
	return guestID;
}


/**
 * Sets the guest ID.
 *
 * @param guestID the new guest ID
 */
public void setGuestID(String guestID) {
	this.guestID = guestID;
}


/**
 * Gets the dog ID.
 *
 * @return the dog ID
 */
public int getDogID() {
	return dogID;
}


/**
 * Sets the dog ID.
 *
 * @param dogID the new dog ID
 */
public void setDogID(int dogID) {
	this.dogID = dogID;
}


/**
 * Gets the employee ID.
 *
 * @return the employee ID
 */
public int getEmployeeID() {
	return employeeID;
}


/**
 * Sets the employee ID.
 *
 * @param employeeID the new employee ID
 */
public void setEmployeeID(int employeeID) {
	this.employeeID = employeeID;
}


/**
 * Gets the state.
 *
 * @return the state
 */
public String getState() {
	return state;
}


/**
 * Sets the state.
 *
 * @param state the new state
 */
public void setState(String state) {
	this.state = state;
}


/**
 * Gets the request date.
 *
 * @return the request date
 */
public LocalDate getRequestDate() {
	return requestDate;
}


/**
 * Sets the request date.
 *
 * @param requestDate the new request date
 */
public void setRequestDate(LocalDate requestDate) {
	requestDate = LocalDate.now();
	this.requestDate = requestDate;
}

   
   
}
