package pt.iade.dsm.models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Period;


/**
 * This model represents the employee.
 */
public class Employee {
		
		/** The employee ID. */
		private int employeeID;
		
		/** The Name. */
		private String  name;
		
		/** The username. */
		private String username;
		
		/** The password. */
		private String password;
		
		/** The gender. */
		private Gender gender;
		
		/** The position held. */
		private EmployeePosition pos_held;
		
		/** The birthdate. */
		private LocalDate birthdate;
		
		/** The photo. */
		private File photo;
			
			/**
			 * Instantiates a new employee.
			 *
			 * @param name the name
			 * @param username the username
			 * @param password the password
			 * @param gender the gender
			 * @param birthdate the birthdate
			 * @param pos_held the pos held
			 * @throws IOException Signals that an I/O exception has occurred.
			 */
			public Employee(String name, String username, String password, Gender gender,
							LocalDate birthdate, EmployeePosition pos_held) throws IOException {
				
				setName(name);
				setUsername(username);
				setPassword(password);
				setGender(gender);
				setBirthdate(birthdate);
				setPos_held(pos_held);
				setPhoto(new File("src/pt/iade/dsm/images/DefaultPerson.png"));
				
			}
			
			/**
			 * Instantiates a new employee.
			 *
			 * @param name the name
			 * @param username the username
			 * @param password the password
			 * @param gender the gender
			 * @param birthdate the birthdate
			 * @param pos_held the pos held
			 * @param photo the photo
			 * @throws IOException Signals that an I/O exception has occurred.
			 */
			public Employee(String name, String username, String password, Gender gender,
							LocalDate birthdate, EmployeePosition pos_held, File photo) throws IOException {
				
				this(name,username,password,gender,birthdate,pos_held);
				setPhoto(photo);
				copyImageFile();
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
			 * Gets the name.
			 *
			 * @return the name
			 */
			public String getName() {
				return name;
			}

			/**
			 * Sets the name.
			 *
			 * @param name the new name
			 */
			public void setName(String name) {
				if(name.length()<50 || name.matches("[a-zA-Z]+"))
					this.name = name;
					//throw new IllegalArgumentException("The name should not be longer than 50 characters or contain any charecter besides letters.");
				else
					throw new IllegalArgumentException("The name should not be longer than 50 characters or contain any charecter besides letters.");
					//this.name = name;
			}

			/**
			 * Gets the username.
			 *
			 * @return the username
			 */
			public String getUsername() {
				return username;
			}

			/**
			 * Sets the username.
			 *
			 * @param username the new username
			 */
			public void setUsername(String username) {
				if(username.length()<8 || username.length()>15)
					throw new IllegalArgumentException("The username must be between 8-15 characteres.");
				else
					this.username = username;
			}
			
			/**
			 * Gets the password.
			 *
			 * @return the password
			 */
			public String getPassword() {
				return password;
			}
			
			
			/**
			 * Sets the password.
			 *
			 * @param password the new password
			 */
			/*
			 * This method ensures that the password length is between 8-15 characters.
			 */
			public void setPassword(String password) {
				if (password.isEmpty() || password.length()<8 || password.length()>15 )
					throw new IllegalArgumentException("The password must be between 8-15 characters.");
				else
				    this.password = password;
			}

			/**
			 * Gets the gender.
			 *
			 * @return the gender
			 */
			public Gender getGender() {
				return gender;
			}

			/**
			 * Sets the gender.
			 *
			 * @param gender the new gender
			 */
			public void setGender(Gender gender) {
				this.gender = gender;
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
			/*
			 * This method certificates that the employee is at least 18yrs old.
			 */
			public void setBirthdate(LocalDate birthdate) {
				 int age = Period.between(birthdate, LocalDate.now()).getYears();
			        
			        if (age >= 18 && age <= 60)
			        	this.birthdate = birthdate;
			        else
			            throw new IllegalArgumentException("An employee must be 18-60 years old.");
				
			}

			/**
			 * Gets the photo.
			 *
			 * @return the photo
			 */
			public File getPhoto() {
				return photo;
			}

			/**
			 * Sets the photo.
			 *
			 * @param photo the new photo
			 */
			public void setPhoto(File photo) {
				this.photo = photo;
			}
			
			/**
			 * Gets the position held.
			 *
			 * @return the pos held
			 */
			public EmployeePosition getPos_held() {
				return pos_held;
			}

			/**
			 * Sets the position held.
			 *
			 * @param pos_held the new pos held
			 */
			public void setPos_held(EmployeePosition pos_held) {
				this.pos_held = pos_held;
			}


			/**
			 * To string.
			 *
			 * @return the string
			 */
			public String toString () {
				return String.format(" %s is a %s that has %d", name, gender, Period.between(birthdate, LocalDate.now()).getYears());
			}
			
			
			
			
			
			/**
			 * Copy image file.
			 *
			 * @throws IOException Signals that an I/O exception has occurred.
			 */
			public void copyImageFile() throws IOException
		    {
		        //create a new Path to copy the image into a local directory
		        Path sourcePath = photo.toPath();
		        
		        String uniqueFileName = getUniqueFileName(photo.getName());
		        
		        Path targetPath = Paths.get("src/pt/iade/dsm/images/Employees/"+uniqueFileName);
		        
		        //copy the file to the new directory
		        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
		        
		        //update the imageFile to point to the new File
		        photo = new File(targetPath.toString());
		    }
			
			/**
			 * Gets the unique file name.
			 *
			 * @param oldFileName the old file name
			 * @return the unique file name
			 */
			private String getUniqueFileName(String oldFileName)
		    {
		        String newName;
		        
		        //create a Random Number Generator
		        SecureRandom rng = new SecureRandom();
		        
		        //loop until we have a unique file name
		        do
		        {
		            newName = "";
		            
		            //generate 10 random characters
		            for (int count=1; count <=10; count++)
		            {
		                int nextChar;
		                
		                do
		                {
		                    nextChar = rng.nextInt(123);
		                } while(!validCharacterValue(nextChar));
		                
		                newName = String.format("%s%c", newName, nextChar);
		            }
		            newName += oldFileName;
		            
		        } while (!uniqueFileInDirectory(newName));
		        
		        return newName;
		    }
		    
		    
		    /**
    		 * Unique file in directory.
    		 *
    		 * @param fileName the file name
    		 * @return true, if successful
    		 */
    		/*
		     * This method will search the images directory and ensure that the file name
		     * is unique
		    */
		    public boolean uniqueFileInDirectory(String fileName)
		    {
		        File directory = new File("src/pt/iade/dsm/images/Employees");
		        
		        File[] dir_contents = directory.listFiles();
		                
		        for (File file: dir_contents)
		        {
		            if (file.getName().equals(fileName))
		                return false;
		        }
		        return true;
		    }
		    
		    /**
    		 * Valid character value.
    		 *
    		 * @param asciiValue the ascii value
    		 * @return true, if successful
    		 */
    		/*
		     * This method will validate if the integer given corresponds to a valid
		     * ASCII character that could be used in a file name
		    */
		    public boolean validCharacterValue(int asciiValue)
		    {
		        
		        //0-9 = ASCII range 48 to 57
		        if (asciiValue >= 48 && asciiValue <= 57)
		            return true;
		     
		        return false;
		    }
		    

}
