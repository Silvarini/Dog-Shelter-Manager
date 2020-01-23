package pt.iade.dsm.models;

import java.time.LocalDate;

/**
 * The Class Registration.
 */
public class Registration {
	
	/** The idinsc. */
	private int idinsc;
	
	/** The idvolunteer. */
	private int idvolunteer;
	
	/** The idevent. */
	private int idevent;
    
    /** The title E. */
    private String titleE;
    
    /** The date. */
    private LocalDate date;
    
    
    
    /**
     * Instantiates a new registration.
     *
     * @param idvolunteer the idvolunteer
     * @param idevent the idevent
     * @param titleE the title E
     * @param date the date
     */
    public Registration(int idvolunteer, int idevent, String titleE, LocalDate date) {
		setIdvolunteer(idvolunteer);
		setIdevent(idevent);
		setTitleE(titleE);
		setDate(date);
	}
 
 
 
	/**
	 * Gets the idinsc.
	 *
	 * @return the idinsc
	 */
	public int getIdinsc() {
		return idinsc;
	}
	
	/**
	 * Sets the idinsc.
	 *
	 * @param idinsc the new idinsc
	 */
	public void setIdinsc(int idinsc) {
		this.idinsc = idinsc;
	}
	
	/**
	 * Gets the idvolunteer.
	 *
	 * @return the idvolunteer
	 */
	public int getIdvolunteer() {
		return idvolunteer;
	}
	
	/**
	 * Sets the idvolunteer.
	 *
	 * @param idvolunteer the new idvolunteer
	 */
	public void setIdvolunteer(int idvolunteer) {
		this.idvolunteer = idvolunteer;
	}
	
	/**
	 * Gets the idevent.
	 *
	 * @return the idevent
	 */
	public int getIdevent() {
		return idevent;
	}
	
	/**
	 * Sets the idevent.
	 *
	 * @param idevent the new idevent
	 */
	public void setIdevent(int idevent) {
		this.idevent = idevent;
	}
	
	/**
	 * Gets the title E.
	 *
	 * @return the title E
	 */
	public String getTitleE() {
		return titleE;
	}
	
	/**
	 * Sets the title E.
	 *
	 * @param titleE the new title E
	 */
	public void setTitleE(String titleE) {
		this.titleE = titleE;
	}



	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}



	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
    
    

}
