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



