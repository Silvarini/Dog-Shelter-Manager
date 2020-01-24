/*Selects the states for the decision of the employee when anwsering a adoption request, returns "accept" and "reject"*/
SELECT adoptionID,requestDate, guestID, dogID, stateTypesID, stateTypes 
FROM AdoptionRequests, StateAdoption 
WHERE state = stateTypesID AND state = 2 OR state = 3;

/*Returns the adoption requests that have the state "on hold"*/
SELECT adoptionID, requestDate,guestID, dogID, stateTypesID, stateTypes 
FROM AdoptionRequests,StateAdoption 
WHERE state = 1 AND stateTypesID = state;

/*Returns all dogs and the attributes associated to them*/
SELECT dogID, nameDog, obs, photoFile, breedID, breed , ageClassID, ageClass, Gender.genderID, Gender.gender, sizeClassID, sizeClass, cLengthClassID, cLengthClass, goodWithID, goodWith, stateID, state 
FROM Dog,AgeClass,Breed,CoatLengthClass, Gender,GoodWith, SizeClass,StateType
WHERE Dog.ageDog = AgeClass.ageClassID AND Dog.breedDog = Breed.breedID AND Dog.coatLengthDog = CoatLengthClass.cLengthClassID AND Dog.genderDog = Gender.genderID AND Dog.goodWithDog = GoodWith.goodWithID AND Dog.currentState = StateType.stateID AND Dog.sizeDog = SizeClass.sizeClassID;

/*Returns dogs avaliable for adoption*/
SELECT dogID,nameDog, obs,photoFile, breedID, breed, ageClassID, ageClass, genderID, gender, sizeClassID, sizeClass,cLengthClassID, cLengthClass, goodWithID, goodWith, stateID, state 
FROM Dog,AgeClass,Breed,CoatLengthClass, Gender,GoodWith, SizeClass,StateType 
WHERE Dog.ageDog = AgeClass.ageClassID AND Dog.breedDog = Breed.breedID AND Dog.coatLengthDog = CoatLengthClass.cLengthClassID AND Dog.genderDog = Gender.genderID AND Dog.goodWithDog = GoodWith.goodWithID AND Dog.currentState = StateType.stateID AND Dog.sizeDog = SizeClass.sizeClassID AND (currentState='4' OR currentState='3');


/*Returns all employees and the attributes associated to them*/
SELECT nameEmployee, employeeID, username,password, genderEmployee, Gender.gender, Gender.genderID, birthdate, positionHeld, EmployeePosition.position, EmployeePosition.positionID, PhotoFile 
FROM Employee, Gender, EmployeePosition 
WHERE genderEmployee = Gender.genderID AND positionHeld = EmployeePosition.positionID;


/*Returns all from table Events*/
SELECT * FROM Events;


/Empregado que mais realizou adoções/
SELECT Employee.employeeID, nameEmployee, count(Historic.employeeID) as "number of adoptions" FROM Employee, Historic WHERE Employee.employeeID = Historic.employeeID and Historic.employeeID = ( 
	SELECT employeeID FROM Historic GROUP BY employeeID HAVING count(employeeID) = ( 
		SELECT count(employeeID) AS great FROM Historic GROUP BY employeeID ORDER BY great desc limit 1) and Historic.stateType=2 and DATEDIFF(now(), Historic.initialDate)<30;

/Nºmortes nos últimos trinta dias/
SELECT COUNT(Historic.stateType) AS "Nº de mortes nos últimos trinta dias"FROM Historic WHERE stateType=1 AND DATEDIFF(now(),initialDate)<30;

/voluntário que mais participou em eventos/
select Volunteer.id, Volunteer.fName, count(Inscricao.idvolunteer) as "Nº of participations" from Volunteer, Inscricao where Volunteer.id = Inscricao.idvolunteer and Inscricao.idvolunteer = (
	select Inscricao.idvolunteer from Inscricao group by Inscricao.idvolunteer having count(Inscricao.idvolunteer) =(		select count(Inscricao.idvolunteer) as great from Inscricao group by Inscricao.idvolunteer order by great desc limit 1 ) limit 1) AND DATEDIFF(now(), Historic.initialDate)<30 ;
