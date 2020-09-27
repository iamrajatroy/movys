# movys
Movie recommendation app using content-based filtering. Data provided by TMDb.


# Demo
![movys demo](demo/movys-demo.gif)


# About
This is a movie recommender application. It recommends similar movies to the movies you like.
The recommendation service is a content based filtering service which reads a movie description and finds other movies with matching description.
This recommendation services is served in a docker container and connected with the Spring Boot App (Java) based backend.
The UI is developed with Angular and is coupled with the Spring Boot Application using Spring Thymeleaf.
All movies and user-liked movies are stored with the help of in-memory db served by Apache Derby.

The overall movies data is downloaded from https://www.themoviedb.org/ using TMDb Developer API.
