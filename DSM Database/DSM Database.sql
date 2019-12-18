create database SHELTER;
use SHELTER;

Create table StateType (state varchar(20) not null,
						constraint STPK primary key (state));
	insert into StateType (state) values ("dead"),("adopted"),("returned"),("not adopted");

create table Gender(Gender varchar(6),
					constraint GenderFK primary key (Gender));
	insert into Gender(Gender) value ("Male"),("Female");
    
create table CoatLengthClass(cLengthClass varchar(6),
							constraint CLPK primary key (cLengthClass));
	insert into CoatLengthClass(cLengthCLass) values ("small"),("medium"),("large");
    
create table GoodWith ( goodWith varchar(15),
						constraint GWPK primary key (goodWith));
	insert into GoodWith(goodWith) values ("Dogs"),("Kids"),("Cats"),("Birds"),("None"),("All Species");
    
create table SizeClass (sizeClass varchar(10),
						constraint SizePK primary key (sizeClass));
	insert into SizeClass(sizeClass) values ("XS"),("small"),("medium"),("large"),("XL");
    
create table Breed (breed varchar(20),
					constraint BreedPK primary key (breed));
	insert into Breed(breed) values ("Beagle"),("Boerboel"),("Anatolian Sheperd"),("Bullboxer Pit"),("Cane Corso"),("Dachsador"),("Labradane"),("Corgi");
    
create table AgeClass ( ageClass varchar(10),
						constraint AgePK primary key (ageClass));
	insert into AgeClass (ageClass)values ("Puppy"),("Young"),("Adult"),("Senior");
    

Create table Dog ( nameDog varchar(20),
				   dogID int auto_increment not null,
                   breedDog varchar(20),
                   ageDog varchar(10),
                   genderDog varchar(6),
                   sizeDog varchar(9),
                   coatLengthDog varchar(6),
                   goodWithDog varchar(4),
                   obs text,
                   photoFile varchar(100),
                   currentState varchar(15),
                   
                   constraint dogPK primary key (dogID),
                   constraint ageFK foreign key (ageDog) references AgeClass(ageCLass),
                   constraint breedFK FOREIGN KEY (breedDog) REFERENCES Breed(breed),
                   constraint genderFK foreign key  (genderDog) REFERENCES Gender(Gender),
                   constraint sizeFK foreign key (sizeDog) references SizeClass(sizeClass),
                   constraint coatFK foreign key (coatLengthDog) references CoatLengthClass(cLengthClass),
                   constraint gwFK foreign key (goodWithDog) references GoodWith(goodWith),
                   constraint cStateFK foreign key (currentState) references StateType(state)
                   );
                    select * from Dog;
                 
                 
                 
                    
                                
 
Create table EmployeePosition ( position varchar(15),
							    constraint EPPK primary key (position));

insert into EmployeePosition(position) values ("Administrador"),("Funcionário");


Create table Employee ( nameEmployee varchar (50),
						employeeID int auto_increment not null,
                        genderEmployee varchar(6),
                        birthdate date,
                        username varchar(15),
                        password varchar(15),
                        positionHeld varchar(15),
                        PhotoFile varchar(100),
                        
                        Constraint EmployeePK primary key (employeeID,username),
                        Constraint EGenderFK foreign key (genderEmployee) references Gender(gender),
                        constraint ephFK foreign key (positionHeld) references EmployeePosition(position));
                        
                   insert into Employee (nameEmployee, genderEmployee,birthdate,username,password,positionHeld,PhotoFile) values ("Nzembo Pitta Grós","Male","2001-04-18","nzembo_gros","12345678","Administrador","DefeaultPerson.png");
				   insert into Employee (nameEmployee, genderEmployee,birthdate,username,password,positionHeld,PhotoFile) values ("Bernardo Silva","Male","1999-01-01","benno_silva","87654321","Administrador","DefaultPerson.png");
                   select * from Employee;
                  
                   
                   
           
                   
Create table Guest ( firstName varchar(15),
					 lastName varchar(15),	
					 phone varchar(11),
                     email varchar(25),
                     adress varchar(50),
                     obs text,
                     
                     Constraint GuestPK primary key (phone));
 select*from Guest;                  





Create table Historic(idHistoric int auto_increment,
					  stateType varchar(15),
					  dogID INT,
					  employeeID int,
					  initialDate datetime default current_timestamp,
                      
                      constraint SHFK foreign key (stateType) references StateType(state),
                      constraint HPK primary key (idHistoric),
					  constraint DIFK foreign key (dogID) references Dog(dogID),
					  constraint EAFK foreign key (employeeID) references Employee(employeeID));
Select * from Historic;


  
  
create table StateAdoption( stateTypes varchar(10),

							constraint SSPK primary key (stateTypes));
                             
insert into StateAdoption values ("on hold"),("accepted"),("rejected");
 
Create table AdoptionRequests (	adoptionID INT auto_increment not null ,
								guestID varchar(11) not null,
                                dogID int not null,
                                state varchar(10),
                                requestDate Date,
                                
                                constraint APK primary key (adoptionID),
                                constraint GFK foreign key (guestID) references Guest(phone),
                                constraint DFK foreign key (dogID) references Dog(dogID),
                                constraint SFK foreign key (state) references StateAdoption(stateTypes)
                        ); 
select * from AdoptionRequests;			




		
					