# doctorAppointment


**Run application 

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

**Technology:-

    1- spring boot
    2- Spring Cloud
    3- firebase NoSql DB
    4- Rest Service

**Future work:

    1- messaging (Using AMQP)
    2- Docker
    3- Endpoints Authentication JWT

**DB Structure Relation:-

    1 - Doctor 
    2 - Patient
    3 - MedicalAppointment [DoctorId] 
          We use this table to store and manage doctor shifts [Medical appointment of doctor]
    4 - Reservation [PatientId, MedicalAppointmentId]
        this table contain all reservation of patient at the specific doctor MedicalAppointment and its' timing
        
        
**why Using Firebase:-

Firebase is a simple and user-friendly mobile and web application development platform developed by Google
Supports the NoSQL cloud database which provides a mechanism for the storage and retrieval of data
It offers,
    1 - Real-time synchronization of data meaning every change is automatically updated to the connected clients
    2 - Scalability across multiple databases
    3 - Different authentication techniques such as anonymous, password, or social
    4 - Application deployment on the firebase servers
    5 - Storing the documents in the json format
