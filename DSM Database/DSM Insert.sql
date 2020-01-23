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
  
  /*Adds 2 Administrators and 5 employees*/
  insert into Employee (nameEmployee, genderEmployee,birthdate,username,password,positionHeld,PhotoFile) values ("Nzembo Pitta Grós",1,"2001-04-18","nzembo_gros","12345678",1,"DefeaultPerson.png");	
  insert into Employee (nameEmployee, genderEmployee,birthdate,username,password,positionHeld,PhotoFile) values ("Bernardo Silva",1,"1999-01-01","benno_silva","87654321",1,"DefaultPerson.png");
  insert into Employee (nameEmployee, genderEmployee,birthdate,username,password,positionHeld,PhotoFile) values ("Ana Santos",2,"2002-03-18","ana_santos","12345678",2,"DefeaultPerson.png");	
  insert into Employee (nameEmployee, genderEmployee,birthdate,username,password,positionHeld,PhotoFile) values ("Pedro Ribeiro",1,"1990-03-10","pedro_lima","12345678",2,"DefeaultPerson.png");	
  insert into Employee (nameEmployee, genderEmployee,birthdate,username,password,positionHeld,PhotoFile) values ("João Lima",1,"1998-01-10","joao_lima","12345678",2,"DefeaultPerson.png");	
  insert into Employee (nameEmployee, genderEmployee,birthdate,username,password,positionHeld,PhotoFile) values ("Joana José",2,"2001-01-05","joana_jose","12345678",2,"DefeaultPerson.png");	
  insert into Employee (nameEmployee, genderEmployee,birthdate,username,password,positionHeld,PhotoFile) values ("Duarte Marques",1,"2001-10-12","duarte_marques","12345678",2,"DefeaultPerson.png");	

 /*States of Adoption*/
  insert into StateAdoption(stateTypes) values ("on hold"),("accepted"),("rejected");
	
  /*Adds 3 Dogs*/	
  insert into Dog (nameDog, dogID, breedDog, ageDog, genderDog,sizeDog,coatLengthDog,goodWithDog,obs,photoFile,currentState) values ("JeffreyII",1 ,5 ,3 ,1 , 2, 4,"He has no health problems.","2062472523Dog2.PNG", 4);	
  insert into Dog (nameDog, dogID, breedDog, ageDog, genderDog,sizeDog,coatLengthDog,goodWithDog,obs,photoFile,currentState) values ("Nicky",3 ,1 ,4 ,2 , 3, 1, 6,"He has no health problems.","2062472523Dog2.PNG", 4);	
  insert into Dog (nameDog, dogID, breedDog, ageDog, genderDog,sizeDog,coatLengthDog,goodWithDog,obs,photoFile,currentState) values ("Waffles",2 ,7 ,4 ,2 , 3, 2, 1,"Traumatized with cars.","9444703745Dog1.PNG", 2);	

  /*Adds 3 adoption requests*/
  insert into AdoptionRequests (adoptionID, guestID, dogID, state, requestDate) values (1, "+256656464211", 1, 1, "2020-01-23");	
  insert into AdoptionRequests (adoptionID, guestID, dogID, state, requestDate) values (2, "+256656652211", 2, 2, "2020-02-01");	
  insert into AdoptionRequests (adoptionID, guestID, dogID, state, requestDate) values (3, "+256965264211", 3, 3, "2020-05-10");	
  
  /*Adds 3 events*/
  insert into Events (id, idE, title, eventDate, description, maxPart, currentPart) values (1, 3, "Fotos", "2020-01-25 00:00:00", "Tirar fotos", 1, 1);
  insert into Events (id, idE, title, eventDate, description, maxPart, currentPart) values (2, 3, "Giving Baths", "2020-01-23 00:00:00", "Giving baths to dogs", 3, 2);
  insert into Events (id, idE, title, eventDate, description, maxPart, currentPart) values (3, 4, "Walking the dogs", "2020-01-21 00:00:00", "Taking the dogs for a nice walk", 5, 0);	

  
  /*Adds 3 guests*/
  insert into Guest (firstname, lastname, phone, email, address, obs) values ("Boris", "Slavic", "+256656464211", "boris.slavic@email.com", "3620 Marcus Street", "House is big");
  insert into Guest (firstname, lastname, phone, email, address, obs) values ("Jorge", "Sonhos", "+351546852845", "jorge.sonhos@email.com", "1895  Parrill Court", "I need a police dog");
  insert into Guest (firstname, lastname, phone, email, address, obs) values ("Tatiana", "Sampaio", "+351587584868", "tatiana.sampaio@email.com", "972 Anmoore Road", "I have a cat");	

  /*Adds 2 registrations of events*/
  insert into Inscricao (idinsc, idvolunteer, idevent, titleE, date) values (1, 1, 1, "Fotos", "2020-01-25");
  insert into Inscricao (idinsc, idvolunteer, idevent, titleE, date) values (2, 2, 2, "Giving Baths", "2020-01-23");
  
  /*Adds 2 volunteers*/
  insert into Volunteer (id, fName, IName, phone, email, adress, password, birthdate) values (1, "Bruno", "Santos", "+123456789987", "bruno@email.com", "Adress", "123", "2000-01-06");
  insert into Volunteer (id, fName, IName, phone, email, adress, password, birthdate) values (2, "Ricardo", "Amoreira", "+351695848525", "ricardo.amoreira@email.com", "11660 Munzel Rd", "123", "1996-01-10");



