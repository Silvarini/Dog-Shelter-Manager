package pt.iade.dsm.models;

import java.time.LocalDate;

/**
 * This model represents the states.
 */
public class Historic {

	
	/** The state. */
	private StateDog state;
	
	/** The dog ID. */
	private int dogID;
	
	/** The employee ID. */
	private int employeeID;
	
	/** The initial date. */
	private LocalDate initialDate;
	
	/** The id of the state. */
	private int id;
	
	
	/**
	 * Instantiates a new state.
	 *
	 * @param state the state
	 * @param dogID the dog ID
	 * @param employeeID the employee ID
	 */
	public Historic(StateDog state, int dogID, int employeeID) {
		setState(state);
		setDogID(dogID);
		setEmployeeID(employeeID);
		setInitialDate(initialDate);
	}
	
	
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public StateDog getState() {
		return state;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(StateDog state) {
		this.state = state;
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
	 * Gets the initial date.
	 *
	 * @return the initial date
	 */
	public LocalDate getInitialDate() {
		return initialDate;
	}
	
	/**
	 * Sets the initial date.
	 *
	 * @param initialDate the new initial date
	 */
	public void setInitialDate(LocalDate initialDate) {
		initialDate = LocalDate.now();
		this.initialDate = initialDate;
		
	}

	/**
	 * Gets the state's id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/**
	 * Sets the state's id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
