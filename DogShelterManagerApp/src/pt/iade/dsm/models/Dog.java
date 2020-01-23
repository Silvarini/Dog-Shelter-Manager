package pt.iade.dsm.models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;




/**
 * This model represents the dog.
 */
public class Dog {
	
	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/** The breed. */
	private Breed breed;
	
	/** The age. */
	private AgeClass age;
	
	/** The gender. */
	private Gender gender;
	
	/** The size. */
	private Size size;
	
	/** The coat length. */
	private CoatLength coat;
	
	/** The good with. */
	private GoodWith goodw;
	
	/** The Observation. */
	private String Obs;
	
	/** The photo. */
	private File photo;
	
	/** The state. */
	private StateDog state;
	
	
	
/**
 * Instantiates a new dog.
 *
 * @param name the name
 * @param breed the breed
 * @param age the age
 * @param gender the gender
 * @param size the size
 * @param coat the coat
 * @param goodw the goodw
 * @param Obs the obs
 * @param state the state
 */
public Dog(String name, Breed breed, AgeClass age, Gender gender, Size size, CoatLength coat, GoodWith goodw, String Obs, StateDog state) {
		setName(name);
		setBreed(breed);
		setAge(age);
		setGender(gender);
		setSize(size);
		setCoat(coat);
		setGoodw(goodw);
		setObs(Obs);
		setState(state);
		setPhoto(new File("src/pt/iade/dsm/images/DefaultDog.png"));
	}

/**
 * Instantiates a new dog.
 *
 * @param name the name
 * @param breed the breed
 * @param age the age
 * @param gender the gender
 * @param size the size
 * @param coat the coat
 * @param goodw the goodw
 * @param Obs the obs
 * @param state the state
 * @param photo the photo
 * @throws IOException Signals that an I/O exception has occurred.
 */
public Dog(String name, Breed breed, AgeClass age, Gender gender, Size size, CoatLength coat, GoodWith goodw, String Obs, File photo, StateDog state) throws IOException {
	this(name,breed,age,gender,size,coat,goodw,Obs,state);
	setPhoto(photo);
	copyImageFile();
	
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
	if(name.length()>0 && name.length()<15 && name.matches("[a-zA-Z]+"))		
		this.name = name;
	else 
		throw new IllegalArgumentException("The name must be shorter than 15 characters and should not contain special characteres or numbers.");
}



/**
 * Gets the breed.
 *
 * @return the breed
 */
public Breed getBreed() {
	return breed;
}



/**
 * Sets the breed.
 *
 * @param breed the new breed
 */
public void setBreed(Breed breed) {
	this.breed = breed;
}



/**
 * Gets the age.
 *
 * @return the age
 */
public AgeClass getAge() {
	return age;
}



/**
 * Sets the age.
 *
 * @param age the new age
 */
public void setAge(AgeClass age) {
	this.age = age;
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
 * Gets the size.
 *
 * @return the size
 */
public Size getSize() {
	return size;
}



/**
 * Sets the size.
 *
 * @param size the new size
 */
public void setSize(Size size) {
	this.size = size;
}



/**
 * Gets the coat length.
 *
 * @return the coat 
 */
public CoatLength getCoat() {
	return coat;
}



/**
 * Sets the coat length.
 *
 * @param coat the new coat
 */
public void setCoat(CoatLength coat) {
	this.coat = coat;
}



/**
 * Gets the good with.
 *
 * @return the goodw
 */
public GoodWith getGoodw() {
	return goodw;
}



/**
 * Sets the good with.
 *
 * @param goodw the new goodw
 */
public void setGoodw(GoodWith goodw) {
	this.goodw = goodw;
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
 * Gets the observation.
 *
 * @return the obs
 */
public String getObs() {
	return Obs;
}

/**
 * Sets the observation.
 *
 * @param obs the new obs
 */
public void setObs(String obs) {
	if(obs.length()==0)
		throw new IllegalArgumentException("Please provide more information.");
	this.Obs = obs;
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
 * Copy image file.
 *
 * @throws IOException Signals that an I/O exception has occurred.
 */
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
    File directory = new File("src/pt/iade/dsm/images/Dogs");
    
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
