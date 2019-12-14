package pt.iade.dsm.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.xml.internal.txw2.IllegalAnnotationException;

public class Guest {
	
	private  String firstName;
	private  String lastName;
	private  String phone;
	private  String email;
	private  String adress;
	private  String obs;
	
	
	
	public Guest(String firstName, String lastName, String phone, String email, String adress, String obs) {
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
		setEmail(email);
		setAdress(adress);
		setObs(obs);
	}




	public String getFirstName() {
		return firstName;
	}



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



	public String getLastName() {
		return lastName;
	}



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



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		//if(phone.matches("[+]\\[3519]\\d{8}"))
			this.phone = phone;
		//else 
			//throw new IllegalArgumentException("The number must follow the pattern +3519XXXXXXXX");
	}



	public String getEmail() {
		return email;
	}


	public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}
	public void setEmail(String email) {
		if(isValidEmailAddress(email))
		this.email = email;
//		throw new IllegalAnnotationException("The email is not valid.");
			else
			throw new IllegalAnnotationException("The email is not valid.");
			//this.email = email;
	}



	public String getObs() {
		return obs;
	}



	public void setObs(String obs) {
		this.obs = obs;
	}


	public String getAdress() {
		return adress;
	}



	public void setAdress(String adress) {
		if(adress.matches("/[!@$%^&*(),?\":{}|<>]/g"))
			throw new IllegalArgumentException("Adress:endereço número andar(se houver) , CP, Cidade");
		else
			this.adress = adress;
	}
	
	

}
