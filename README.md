# Java RESTEasy Hibernate
This is a simple note taking application called Knook. Also, this project is a basic example of building REST API with Java 7, RESTEasy, Hibernate and other technologies.

## Technologies
- Java 7
- RESTEasy (JAX-RS)
- Hibernate (JPA)
- Maven (build)
- Glassfish (deployment)
- MySQL (data storage)

## Features
Knook provides full CRUD operations for user management.

## Getting Started

To get you started you need to install dependencies, clone the repository, deploy and run the application with your favorite IDE.

### Prerequisites

You need to have a git client to clone the repository. You can get it from [http://git-scm.com/](http://git-scm.com/).

Also you must have Java 7, Maven to build the application, Glassfish to deploy and run it and MySQL database server to store the data. In addition, it is expected that you will use IDE to build and deploy Knook.

### Installation

1. Install MySQL server
   ```
   sudo apt-get install mysql-server
   ```

1. Install Java JRE and JDK
   ```
   sudo apt-get install default-jre
   sudo apt-get install default-jdk
   ```

1. Clone the java-resteasy-hibernate repository using git:

    ```
    git clone https://github.com/romandunets/java-resteasy-hibernate.git
    ```

1. Use your favorite IDE to deploy and run the application. Particularly, for this project were used Netbeans 8.1.

## Services
There are one main service, **users**

The route for this is

- /users

### List users
You can list all users making a GET request to ```/users.json```

### Get user
You can get one user making a GET request to ```/users/1.json``` (replace 1 by a target user id)

### Create user
You can create an new user making a POST request to ```/users.json``` with the following parameters
```
{ "email": "johndoe@example.com", "password": "password" }
```

### Update user
You can create an new user making a PUT request to ```/users/1.json``` (replace 1 by a target user id) with the following parameters
```
{ "email": "johndoe@example.com", "password": "password" }
```

### Delete user
You can delete an existing user making a DELETE request to ```/users/1.json``` (replace 1 by a target user id)
