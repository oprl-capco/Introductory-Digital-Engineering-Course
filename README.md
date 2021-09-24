# Introductory-Digital-Engineering-Course
This is a simple locally hosted Java Spring Boot application that uses HTTP requests to retrieve first and last names from a MongoDB database. The database is hosted on a local Docker container.

A GET request with the `/users` endpoint should output all users in the database and a GET request of `/users/{userid}` should return the specified user if exists. Otherwise this should raise a 404 response code.

A POST request with the endpoint `/users?name1={firstname}&name2={lastname}` should insert a record into the document within the database, containing the specified first name and last name aswell as a primary key. Incorrect parameter names that are not `name1` and `name2` should return a 400 response code.
 
