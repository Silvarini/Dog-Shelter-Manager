package pt.iade.dsm.models;

import java.time.LocalDate;

/**
 * The Class Event.
 */
public class Event {
	
	/** The id. */
	private int id; 
	
	/** The id E. */
	private int idE;
    
	/** The title. */
	private String title;
    
	/** The event date. */
	private LocalDate eventDate;
    
	/** The description. */
	private String description;
    
	/** The max part. */
	private int  maxPart;
    
	/** The current part. */
	private int currentPart;
    
	
	/**
	 * Instantiates a new event.
	 *
	 * @param idE the id E
	 * @param title the title
	 * @param eventDate the event date
	 * @param description the description
	 * @param maxPart the max part
	 */
	public Event(int idE, String title, LocalDate eventDate, String description, int maxPart) {
		setIdE(idE);
		setTitle(title);
		setEventDate(eventDate);
		setDescription(description);
		setMaxPart(maxPart);
		setCurrentPart(0);
	}
	
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	/**
	 * Gets the id E.
	 *
	 * @return the id E
	 */
	public int getIdE() {
		return idE;
	}
	
	/**
	 * Sets the id E.
	 *
	 * @param idE the new id E
	 */
	public void setIdE(int idE) {
		this.idE = idE;
	}
	
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		if(title.length()<4)
			throw new IllegalArgumentException("The title is too short.");
		else	
			this.title = title;
	}
	
	
	/**
	 * Gets the event date.
	 *
	 * @return the event date
	 */
	public LocalDate getEventDate() {
		return eventDate;
	}
	
	/**
	 * Sets the event date.
	 *
	 * @param eventDate the new event date
	 */
	public void setEventDate(LocalDate eventDate) {
			this.eventDate = eventDate;
	}
	
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		if(description.length() >= 0 && description.length() <=10)
			throw new IllegalArgumentException("Please provide more information.");
		else	
			this.description = description;
	}
	
	
	/**
	 * Gets the max part.
	 *
	 * @return the max part
	 */
	public int getMaxPart() {
		return maxPart;
	}
	
	/**
	 * Sets the max part.
	 *
	 * @param maxPart the new max part
	 */
	public void setMaxPart(int maxPart) {
		if(maxPart >= 1 && maxPart <=100)
			this.maxPart = maxPart;
		else
			throw new IllegalArgumentException("The number must be between 1 and 100.");
	}
	
	
	/**
	 * Gets the current part.
	 *
	 * @return the current part
	 */
	public int getCurrentPart() {
		return currentPart;
	}
	
	/**
	 * Sets the current part.
	 *
	 * @param currentPart the new current part
	 */
	public void setCurrentPart(int currentPart) {
		if(currentPart >= 0 && currentPart <= 100 )
			this.currentPart = currentPart;
	}
}
