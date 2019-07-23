# doctorAppointment

Build a simple Appointment web application between doctors and patients.

**Run application:-** 

    1. maven update
    2. maven clean install
    3. run this miroservice 
        a - eureka-service 
        b - zuul-service 
        c - config-service]
    4. then run other microservice 
        a - doctor-service
        b - patient-service
        c - appointment-service
        d - reservation-service
    5. By using postman App - Import postman files for testing serices

**Basic Services URL:-** 
    
    [x] Doctor Service: http://localhost:8111/docappointment/api/doctor/
    [x] Patient Service: http://localhost:8111/docappointment/api/patient/
    [x] Medical Appointment Service: http://localhost:8111/docappointment/api/apppointment/
    [x] Reservation Service: http://localhost:8111/docappointment/api/reservation/
    
**How to use program:-**

    [x] First you need to add patients and Doctors
    [x] then add Medical appointment of each doctor with determind day and time [from and to] 
    [x] then patient can make reservation on spacific medical appointment if the capacity of medical appointment doesn't reach its' maximum.  

**Technology:-**

    1- spring boot
    2- Spring Cloud
    3- firebase NoSql DB
    4- Rest Service

**Future work:-**

    1- Endpoints Authentication JWT (Registration)
    2- Docker
    3- messaging (Using AMQP) 
        [increase the number of currentPatientCappacity on medicalAppointment when we add new reservation]
        [current we call rest service to make this behavior]

**DB Structure Relation:-**

    1 - Doctor 
    2 - Patient
    3 - MedicalAppointment [DoctorId] 
          We use this table to store and manage doctor shifts [Medical appointment of doctor]
    4 - Reservation [PatientId, MedicalAppointmentId]
        this table contain all reservation of patient at the specific doctor MedicalAppointment and its' timing
        
        
**why Using Firebase:-**

Firebase is a simple and user-friendly mobile and web application development platform developed by Google
Supports the NoSQL cloud database which provides a mechanism for the storage and retrieval of data
It offers,
    [x] Real-time synchronization of data meaning every change is automatically updated to the connected clients
    [x] Scalability across multiple databases
    [x] Different authentication techniques such as anonymous, password, or social
    [x] Application deployment on the firebase servers
    [x] Storing the documents in the json format
