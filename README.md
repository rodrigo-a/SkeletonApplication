# SkeletonApplication

Hi, my name is Rodrigo.
This is a project for a job application.


I'm using Minmaxing CSS styles from https://html5up.net/

# How to run

Pre-requisites: Maven, Tomcat 7, MySql Server. All of them installed and running.

Download the .zip file from this repository. 
Extract the project folder.
Compile with Maven (mvn compile)
Package the compiled project (mvn package)
Copy the generated .war file (SkeletonApp.war) into the deplyment folder of your Tomcat 7 server.

To access the application:
http://localhost:8080/SkeletonApp/faces/xhtml/index.xhtml


To access the rest endpoint:
* Replace {} markers by the corresponding data

- List all Users:
http://localhost:8080/SkeletonApp/rest/User/list 

- Read User
http://localhost:8080/SkeletonApp/rest/User/read/{ID} 

- Create User
http://localhost:8080/SkeletonApp/rest/User/create/{NAME}/{DESCRIPTION}

- Delete User
http://localhost:8080/SkeletonApp/rest/User/delete/{ID}

- Update User
http://localhost:8080/SkeletonApp/rest/User/update/{ID}/{NAME}/{DESCRIPTION}
