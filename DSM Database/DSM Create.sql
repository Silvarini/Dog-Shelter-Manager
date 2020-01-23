
/*Dog States*/
CREATE TABLE StateType (stateID int AUTO_INCREMENT not null, state varchar(20) not null,
			constraint STPK primary key (stateID));
/*Genders*/   
CREATE TABLE Gender(genderID int AUTO_INCREMENT not null, gender varchar(6),
		    constraint GIPK primary key (genderID));
/*Dog Coat Length Class*/    
CREATE TABLE CoatLengthClass(cLengthClassID int AUTO_INCREMENT not null, cLengthClass varchar(6),
			     constraint CLPK primary key (cLengthClassID));
/*Dog Good With Class (interaction with others)*/
CREATE TABLE GoodWith ( goodWithID int AUTO_INCREMENT not null, goodWith varchar(15),
		        constraint GWPK primary key (goodWithID));
/*Dog Size Class*/    
CREATE TABLE SizeClass (sizeClassID int AUTO_INCREMENT not null, sizeClass varchar(10),
			constraint SizePK primary key (sizeClassID));
/*Breeds*/    
CREATE TABLE Breed (breedID int AUTO_INCREMENT not null, breed varchar(20),
		    constraint BreedPK primary key (breedID));
/*Dog Age Class*/    
CREATE TABLE AgeClass ( ageClassID int AUTO_INCREMENT not null, ageClass varchar(10),
			constraint AgePK primary key (ageClassID));
    
/*Dog*/
CREATE TABLE Dog ( nameDog varchar(20),
		   dogID int auto_increment not null,
                   breedDog int,
                   ageDog int,
                   genderDog int,
                   sizeDog int,
                   coatLengthDog int,
                   goodWithDog int,
                   obs text,
                   photoFile varchar(100),
                   currentState int,
                   
                   constraint dogPK primary key (dogID),
                   constraint ageFK foreign key (ageDog) references AgeClass(ageCLassID)
		ON UPDATE CASCADE,
                   constraint breedFK FOREIGN KEY (breedDog) REFERENCES Breed(breedID)
		ON UPDATE CASCADE,
                   constraint genderFK foreign key  (genderDog) REFERENCES Gender(genderID)
		ON UPDATE CASCADE,
                   constraint sizeFK foreign key (sizeDog) references SizeClass(sizeClassID)
		ON UPDATE CASCADE,
                   constraint coatFK foreign key (coatLengthDog) references CoatLengthClass(cLengthClassID)
		ON UPDATE CASCADE,
                   constraint gwFK foreign key (goodWithDog) references GoodWith(goodWithID)
		ON UPDATE CASCADE,
                   constraint cStateFK foreign key (currentState) references StateType(stateID)
		ON UPDATE CASCADE
                   );
                 
                 
                 
                    
                                
/*Employee Roles*/ 
CREATE TABLE EmployeePosition ( positionID int AUTO_INCREMENT not null, position varchar(15),
			        constraint EPPK primary key (positionID));

/*Employees*/
CREATE TABLE Employee ( nameEmployee varchar (50),
			employeeID int auto_increment not null,
                        genderEmployee int,
                        birthdate date,
                        username varchar(15) NOT NULL UNIQUE,
                        password varchar(15) NOT NULL,
                        positionHeld int,
                        PhotoFile varchar(100) NOT NULL,
                        
                   constraint EmployeePK primary key (employeeID,username),
                   constraint EGenderFK foreign key (genderEmployee) references Gender(genderID)
		ON UPDATE CASCADE,
                   constraint ephFK foreign key (positionHeld) references EmployeePosition(positionID)
		ON UPDATE CASCADE
		);
                        
                   
                  
                   
                   
           
/*Guests*/                  
CREATE TABLE Guest ( firstName varchar(15),
		     lastName varchar(15),	
		     phone varchar(13) NOT NULL UNIQUE,
                     email varchar(25) NOT NULL UNIQUE,
                     adress varchar(50),
                     obs text,
                     
                  constraint GuestPK primary key (phone));




/*Historic*/
CREATE TABLE Historic(idHistoric int auto_increment,
		      stateType int,
		      dogID INT,
		      employeeID int,
		      initialDate datetime default current_timestamp,
                      
		   constraint HPK primary key (idHistoric),
                   constraint SHFK foreign key (stateType) references StateType(stateID),
		   constraint DIFK foreign key (dogID) references Dog(dogID)
		ON UPDATE CASCADE,
		   constraint EAFK foreign key (employeeID) references Employee(employeeID)
		ON UPDATE CASCADE
		);


 /*States of Adoption*/ 
CREATE TABLE StateAdoption( stateTypesID int AUTO_INCREMENT not null, stateTypes varchar(10),
			    constraint SSPK primary key (stateTypesID));
                             
/*Adoption Requests*/			     
CREATE TABLE AdoptionRequests (	adoptionID INT auto_increment not null ,
				guestID varchar(13) not null,
                                dogID int not null,
                                state int,
                                requestDate Date,
                                
                   constraint APK primary key (adoptionID),
                   constraint GFK foreign key (guestID) references Guest(phone)
		ON UPDATE CASCADE,
                   constraint DFK foreign key (dogID) references Dog(dogID)
		ON UPDATE CASCADE,
                   constraint SFK foreign key (state) references StateAdoption(stateTypesID)
		ON UPDATE CASCADE
                        ); 


/*Events*/
CREATE TABLE Events ( id int auto_increment not null,
		      idE int not null,
                      title varchar(20) not null,
                      eventDate datetime not null,
                      description Text not null,
                      maxPart int not null,
                      currentPart int not null,
				    
		   constraint idPK primary key (id,title),
                   constraint ideFK foreign key (idE) references Employee(employeeID)
		ON UPDATE CASCADE
                    );


/*Volunteer*/                      
CREATE TABLE Volunteer( id int auto_increment,
			fName varchar(14) not null,
                        lName varchar(20) not null,
                        phone varchar(13) not null,
                        email varchar(50) not null,
                        adress varchar(120) not null,
                        password varchar(10) not null,
                        birthdate date not null,
                        
                     constraint ipPK primary key (id, phone, email));
		     
		     
/*Registrations of volunteers in events*/
CREATE TABLE Inscricao( idinsc int auto_increment,
			idvolunteer int not null,
			idevent int not null, 
                        titleE varchar(20) not null,
                        date Date not null,
                        
                   constraint idvFK primary key (idinsc),
                   constraint idvFK foreign key (idvolunteer) references Volunteer(id)
		ON UPDATE CASCADE,
                   constraint ideiFK foreign key (idevent) references Events(id)
		ON UPDATE CASCADE
		);
					
