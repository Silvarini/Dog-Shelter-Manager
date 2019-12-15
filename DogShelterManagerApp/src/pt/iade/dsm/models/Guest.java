package pt.iade.dsm.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.xml.internal.txw2.IllegalAnnotationException;

/**
 * This model represents the guest.
 */
public class Guest {
	
	/** The first name. */
	private  String firstName;
	
	/** The last name. */
	private  String lastName;
	
	/** The phone. */
	private  String phone;
	
	/** The email. */
	private  String email;
	
	/** The adress. */
	private  String adress;
	
	/** The observation. */
	private  String obs;
	
	
	
	/**
	 * Instantiates a new guest.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param phone the phone
	 * @param email the email
	 * @param adress the adress
	 * @param obs the obs
	 */
	public Guest(String firstName, String lastName, String phone, String email, String adress, String obs) {
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
		setEmail(email);
		setAdress(adress);
		setObs(obs);
	}




	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}



	/**
	 * Sets the first name.
	 * Condition only allows at least 15 characters
	 *
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		if (firstName.length()>15) 
			throw new IllegalArgumentException("The First Name must be smaller than 15 characteres.");
		
		boolean hasDigit = firstName.matches(".*\\d+.*");
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(firstName);
		boolean b = m.find();
		
		if (hasDigit || b)
			throw new IllegalArgumentException("The choosen characteres are not allowed.");
		
		else
		this.firstName = firstName;
	}



	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}



	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		if (lastName.length()>15) 
			throw new IllegalArgumentException("The First Name must be smaller than 15 characteres.");
		
		boolean hasDigit = lastName.matches(".*\\d+.*");
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(lastName);
		boolean b = m.find();
		
		if (hasDigit || b)
			throw new IllegalArgumentException("The choosen characteres are not allowed.");
		
		else
		this.lastName = lastName;
	}



	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}



	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		//if(phone.matches("[+]\\[3519]\\d{8}"))
			this.phone = phone;
		//else 
			//throw new IllegalArgumentException("The number must follow the pattern +3519XXXXXXXX");
	}



	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * Checks if is valid email address.
	 *
	 * @param email the email
	 * @return true, if is valid email address
	 */
	public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		if(isValidEmailAddress(email))
		this.email = email;
//		throw new IllegalAnnotationException("The email is not valid.");
			else
			throw new IllegalAnnotationException("The email is not valid.");
			//this.email = email;
	}



	/**
	 * Gets the obs.
	 *
	 * @return the obs
	 */
	public String getObs() {
		return obs;
	}



	/**
	 * Sets the obsservation.
	 *
	 * @param obs the new obs
	 */
	public void setObs(String obs) {
		this.obs = obs;
	}


	/**
	 * Gets the adress.
	 *
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}



	/**
	 * Sets the adress.
	 *
	 * @param adress the new adress
	 */
	public void setAdress(String adress) {
		if(adress.matches("/[!@$%^&*(),?\":{}|<>]/g"))
			throw new IllegalArgumentException("Adress:endere�o n�mero andar(se houver) , CP, Cidade");
		else
			this.adress = adress;
	}
	
	

}
