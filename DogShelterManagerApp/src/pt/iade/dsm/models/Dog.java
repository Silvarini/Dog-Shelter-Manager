package pt.iade.dsm.models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;




public class Dog {
	private int id;
	private String name;
	private String breed;
	private String age;
	private String gender;
	private String size;
	private String coat;
	private String goodw;
	private String Obs;
	private File photo;
	private String state;
	
	
	
public Dog(String name, String breed, String age, String gender, String size, String coat, String goodw, String Obs) {
		setName(name);
		setBreed(breed);
		setAge(age);
		setGender(gender);
		setSize(size);
		setCoat(coat);
		setGoodw(goodw);
		setObs(Obs);
		setState("not adopted");
		setPhoto(new File("src/pt/iade/dsm/images/DefaultDog.png"));
	}

public Dog(String name, String breed, String age, String gender, String size, String coat, String goodw, String Obs, File photo) throws IOException {
	this(name,breed,age,gender,size,coat,goodw,Obs);
	setState(state);
	setPhoto(photo);
	copyImageFile();
	
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}
	

public String getName() {
	return name;
}



public void setName(String name) {
	this.name = name;
}



public String getBreed() {
	return breed;
}



public void setBreed(String breed) {
	this.breed = breed;
}



public String getAge() {
	return age;
}



public void setAge(String age) {
	this.age = age;
}
	


public String getGender() {
	return gender;
}



public void setGender(String gender) {
	this.gender = gender;
}



public String getSize() {
	return size;
}



public void setSize(String size) {
	this.size = size;
}



public String getCoat() {
	return coat;
}



public void setCoat(String coat) {
	this.coat = coat;
}



public String getGoodw() {
	return goodw;
}



public void setGoodw(String goodw) {
	this.goodw = goodw;
}



public File getPhoto() {
	return photo;
}

public void setPhoto(File photo) {
	this.photo = photo;
	
}	



public String getObs() {
	return Obs;
}

public void setObs(String obs) {
	this.Obs = obs;
}



public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}



public void copyImageFile() throws IOException
{
    //create a new Path to copy the image into a local directory
    Path sourcePath = photo.toPath();
    
    String uniqueFileName = getUniqueFileName(photo.getName());
    
    Path targetPath = Paths.get("src/pt/iade/dsm/images/Dogs/"+uniqueFileName);
    
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
    File directory = new File("src/pt/iade/dsm/images/Dogs");
    
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
