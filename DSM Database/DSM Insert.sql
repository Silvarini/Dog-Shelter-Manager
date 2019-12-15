use SHELTER;
insert into StateType (state) values ("dead"),("adopted"),("returned"),("not adopted");

insert into Gender (Gender) value ("M"),("F");

insert into CoatLengthClass (cLengthCLass) values ("small"),("medium"),("large");

insert into GoodWith (goodWith) values ("Dogs"),("Kids"),("Cats"),("Birds"),("None"),("All Species");

insert into SizeClass (sizeClass) values ("XS"),("small"),("medium"),("large"),("XL");

insert into Breed (breed) values ("Beagle"),("Boerboel"),("Anatolian Sheperd"),("Bullboxer Pit"),("Cane Corso"),("Dachsador"),("Labradane"),("Corgi");

insert into AgeClass (ageClass)values ("Puppy"),("Young"),("Adult"),("Senior");

insert into EmployeePosition (position) values ("Administrador"),("Funcionário");

insert into Employee (nameEmployee, genderEmployee,birthdate,username,password,positionHeld,PhotoFile) values ("Nzembo Pitta Grós","M","2001-04-18","nzembo_gros","12345678","Administrador","DefeaultPerson.png");

insert into Employee (nameEmployee, genderEmployee,birthdate,username,password,positionHeld,PhotoFile) values ("Bernardo Silva","M","1999-01-01","benno_silva","87654321","Administrador","DefaultPerson.png");

insert into StateAdoption values ("on hold"),("accepted"),("rejected");
