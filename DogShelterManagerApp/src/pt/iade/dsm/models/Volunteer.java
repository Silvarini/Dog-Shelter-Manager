package pt.iade.dsm.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class Volunteer.
 */
public class Volunteer {
	
	/** The id. */
	private int idV;

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
	
	/** The birthdate. */
	private LocalDate birthdate;
	
	/**  The password. */
	private String pass;
	
	
	/**
	 * Instantiates a new volunteer.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param phone the phone
	 * @param email the email
	 * @param adress the adress
	 * @param pass the pass
	 * @param birthdate the birthdate
	 */
	public Volunteer (String firstName, String lastName, String phone, String email, String adress, String pass, LocalDate birthdate) {
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
		setEmail(email);
		setAdress(adress);
		setPass(pass);
		setBirthdate(birthdate);
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
			throw new IllegalArgumentException("The choosen characteres for the name are not allowed.");
		
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
			throw new IllegalArgumentException("The choosen characteres for the name are not allowed.");
		
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
	 * Verify PN.
	 *
	 * @param phone the phone
	 * @return true, if successful
	 */
	public boolean verifyPN(String phone) {
	 String ePattern = "^[+]?\\d{12}";
     java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
     java.util.regex.Matcher m = p.matcher(phone);
     return m.matches();
	}
     /**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		if(verifyPN(phone))
			this.phone = phone;
		else 
			throw new IllegalArgumentException("The number must follow the pattern +CCCNNXXXXXXX");
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
	 */
	private static final String EMAIL_REGEX =
			"^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

	/** The Constant EMAIL_PATTERN. */
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	/**
	 * Email validator.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	public static boolean emailValidator(String email) {

		if (email == null) {
			return false;
		}

		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	

	public void setEmail(String email) {
		if(emailValidator(email))
			this.email = email;
		else
			throw new IllegalArgumentException("The email is not valid.");
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
		this.adress = adress;
	}


	/**
	 * Gets the id V.
	 *
	 * @return the id V
	 */
	public int getIdV() {
		return idV;
	}


	/**
	 * Sets the id V.
	 *
	 * @param idV the new id V
	 */
	public void setIdV(int idV) {
		this.idV = idV;
	}


	/**
	 * Gets the pass.
	 *
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}


	/**
	 * Sets the pass.
	 *
	 * @param pass the new pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}


	/**
	 * Gets the birthdate.
	 *
	 * @return the birthdate
	 */
	public LocalDate getBirthdate() {
		return birthdate;
	}


	/**
	 * Sets the birthdate.
	 *
	 * @param birthdate the new birthdate
	 */
	public void setBirthdate(LocalDate birthdate) {
		 int age = Period.between(birthdate, LocalDate.now()).getYears();
	        
	        if (age >= 18 && age <= 60)
	        	this.birthdate = birthdate;
	        else
	            throw new IllegalArgumentException("An employee must be 18-60 years old.");
	}
}