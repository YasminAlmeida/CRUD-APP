# CRUD-APP

<h2> Spring Boot "Microservice" Project</h2>

This is a sample Java / Maven / Spring Boot (version 1.5.6) application that can be used as a starter for creating a task and user manager.

## How to Run 

This application is packaged as a war which has Tomcat 8 embedded. N

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running 
```mvn clean package```

Once the application runs you should see something like this

```
2023-01-05T14:03:28.403-04:00  INFO 22560 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2023-01-05T14:03:28.409-04:00  INFO 22560 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2023-01-05T14:03:29.331-04:00  INFO 22560 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-01-05T14:03:29.349-04:00  INFO 22560 --- [           main] com.trainingcode.Main                    : Started Main in 5.229 seconds (process running for 5.617)
```

## About the Service

The service is just a simple service for creating a task and user manager. It uses an in-memory database (H2), to access the database just access the port: 

```
http://localhost:8080/h2-console
```
Here are some endpoints you can call:

### Get information about tasks, users, etc.

```
http://localhost:8080/tasks
http://localhost:8080/users
http://localhost:8080/tasks/users/1
http://localhost:8080/tasks/search?/user_id=/status_id=/priority_id=/category_id=
```

### Create a task resource

```
POST /tasks
Accept: application/json
Content-Type: application/json

{
    "taskStatus": "Open",
    "priorities": "LOW",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin quis lectus sit amet eros tincidunt maximus. Curabitur ullamcorper tristique nunc.",
    "user": {
         "id": 1
    },
    "category": {
         "id": 3,
    }
}
```
RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/tasks/users/1

### Update a task resource

```
PUT /tasks/id
Accept: application/json
Content-Type: application/json

{
    "taskStatus": "Open",
    "priorities": "LOW",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin quis lectus sit amet eros tincidunt maximus. Curabitur ullamcorper tristique nunc.",
    "user": {
         "id": 1
    },
    "category": {
         "id": 3,
    }
}
```
RESPONSE: HTTP 200 (OK)

### Delete a task resource

```
DELETE /tasks/id
Accept: application/json
Content-Type: application/json

{
   "id": id
}
```
RESPONSE: HTTP 204 (No Content)

---------------------------------------

POST /users
Accept: application/json
Content-Type: application/json

{
    "name": "Maria Brown",
    "email": "maria@gmail.com",
    "phone": "988888888",
    "password": "123456"
}
```
RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/users/id

### Update a User

```
PUT /users/id
Accept: application/json
Content-Type: application/json

{
    "name": "Maria Brown",
    "email": "maria@gmail.com"
}
```
RESPONSE: HTTP 200 (OK)

### Delete a User

```
DELETE /users/id
Accept: application/json
Content-Type: application/json

{
   "id": id
}
```
RESPONSE: HTTP 204 (No Content)
