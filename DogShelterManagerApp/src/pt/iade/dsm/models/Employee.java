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


public class Employee {
		
		private int employeeID;
		private String  Name;
		private String username;
		private String password;
		private String gender;
		private String pos_held;
		private LocalDate birthdate;
		private File photo;
			
			public Employee(String name, String username, String password, String gender,
							LocalDate birthdate, String pos_held) throws IOException {
				
				setName(name);
				setUsername(username);
				setPassword(password);
				setGender(gender);
				setBirthdate(birthdate);
				setPos_held(pos_held);
				setPhoto(new File("src/pt/iade/dsm/images/DefaultPerson.png"));
				
			}
			
			public Employee(String name, String username, String password, String gender,
							LocalDate birthdate, String pos_held, File photo) throws IOException {
				
				this(name,username,password,gender,birthdate,pos_held);
				setPhoto(photo);
				copyImageFile();
			}

			public int getEmployeeID() {
				return employeeID;
			}

			public void setEmployeeID(int employeeID) {
				this.employeeID = employeeID;
			}


			public String getName() {
				return Name;
			}

			public void setName(String name) {
				if(name.length()>50)
					throw new IllegalArgumentException("The name is too big.");
				else
				this.Name = name;
			}

			public String getUsername() {
				return username;
			}

			public void setUsername(String username) {
				if(username.length()<8 || username.length()>15)
					throw new IllegalArgumentException("The username must be between 8-15 characteres.");
				else
					this.username = username;
			}
			
			public String getPassword() {
				return password;
			}
			
			
			/*
			 * This method ensures that the password length is between 8-15 characters.
			 */
			public void setPassword(String password) {
				if (password.isEmpty() || password.length()<8 || password.length()>15 )
					throw new IllegalArgumentException("The password must be between 8-15 characters.");
				else
				    this.password = password;
			}

			public String getGender() {
				return gender;
			}

			public void setGender(String gender) {
				this.gender = gender;
			}

			public LocalDate getBirthdate() {
				
				return birthdate;
			}
			
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

			public File getPhoto() {
				return photo;
			}

			public void setPhoto(File photo) {
				this.photo = photo;
			}
			
			public String getPos_held() {
				return pos_held;
			}

			public void setPos_held(String pos_held) {
				this.pos_held = pos_held;
			}


			public String toString () {
				return String.format(" %s is a %s that has %d", Name, gender, Period.between(birthdate, LocalDate.now()).getYears());
			}
			
			
			
			
			
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
