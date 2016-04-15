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
Knook provides full CRUD operations for user and note management.

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

## API Endpoints
There are two main services, **users**, **groups** and **notes**

The routes for these are

- /users
- /users/{id}/groups
- /users/{id}/groups/{id}/notes

### List users
You can list all users making a GET request to ```/users.json```

### Get user
You can get one user making a GET request to ```/users/{id}.json```

### Create user
You can create an new user making a POST request to ```/users.json``` with the following parameters
```
{ "email": "johndoe@example.com", "password": "password" }
```

### Update user
You can update an existing user making a PUT request to ```/users/{id}.json``` with the following parameters
```
{ "email": "johndoe@example.com", "password": "password" }
```

### Delete user
You can delete an existing user making a DELETE request to ```/users/{id}.json```

### List groups
You can list all groups owned by user, making a GET request to ```/users/{id}/groups.json```

### Get group
You can get one group for owned by user, making a GET request to ```/users/{id}/groups/{id}.json```

### Create group
You can create an new group owned by user, making a POST request to ```/users/{id}/groups/{id}.json``` with the following parameters
```
{ "name": "Some group", "description": "Some description..." }
```

### Update group
You can update an existing group owned by user, making a PUT request to ```/users/{id}/groups/{id}.json``` with the following parameters
```
{ "name": "Some group", "description": "Some description..." }
```

### Delete group
You can delete an existing group owned by user, making a DELETE request to ```/users/{id}/groups/{id}.json```

### List notes
You can list all notes for a specific group owned by user, making a GET request to ```/users/{id}/groups/{id}/notes.json```

### Get note
You can get one note for a specific group owned by user, making a GET request to ```/users/{id}/groups/{id}/notes/{id}.json```

### Create note
You can create an new note for a specific group owned by user, making a POST request to ```/users/{id}/groups/{id}/notes.json``` with the following parameters
```
{ "title": "First note", "content": "Some note..." }
```

### Update note
You can update an existing note for a specific group owned by user, making a PUT request to ```/users/{id}/groups/{id}/notes/{id}.json``` with the following parameters
```
{ "title": "Another note", "content": "Another note..." }
```

### Delete note
You can delete an existing note for a specific group owned by user, making a DELETE request to ```/users/{id}/groups/{id}/notes/{id}.json```

## Further Development

- Attachments for notes
- Relationships
- Authentication
- Icons for groups
- Icons for notes
- Filters for resources
