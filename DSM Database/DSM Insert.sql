  /*Dog States*/
	insert into StateType (state) values ("dead"),("adopted"),("returned"),("not adopted");
  
  /*Genders*/
  insert into Gender(Gender) value ("Male"),("Female");
  
  /*Dog CoatLength Class*/  
  insert into CoatLengthClass(cLengthCLass) values ("small"),("medium"),("large");
  
  /*Dog Good With Class (his interaction with others)*/
	insert into GoodWith(goodWith) values ("Dogs"),("Kids"),("Cats"),("Birds"),("None"),("All Species");
  
  /*Dog Size Class*/  
  insert into SizeClass(sizeClass) values ("XS"),("small"),("medium"),("large"),("XL");
  
  /*Breeds*/ 
  insert into Breed(breed) values ("Beagle"),("Boerboel"),("Anatolian Sheperd"),("Bullboxer Pit"),("Cane Corso"),("Dachsador"),("Labradane"),("Corgi");
  
  /*Dog AgeClass*/
	insert into AgeClass (ageClass)values ("Puppy"),("Young"),("Adult"),("Senior");
  
  /*Employee Roles*/
  insert into EmployeePosition(position) values ("Administrador"),("Funcionário");
  
  /*Adds 2 Administrators*/
  insert into Employee (nameEmployee, genderEmployee,birthdate,username,password,positionHeld,PhotoFile) values ("Nzembo Pitta Grós",1,"2001-04-18","nzembo_gros","12345678",1,"DefeaultPerson.png");
	
  insert into Employee (nameEmployee, genderEmployee,birthdate,username,password,positionHeld,PhotoFile) values ("Bernardo Silva",1,"1999-01-01","benno_silva","87654321",1,"DefaultPerson.png");
 
 /*States of Adoption*/
  insert into StateAdoption(stateTypes) values ("on hold"),("accepted"),("rejected");

