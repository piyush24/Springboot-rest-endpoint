# Springboot-rest-endpoint
Simple rest-end point using springboot , Jpa , Hibernate

I have implemented a simple application which works with 3 REST APIs.

I have used Spring IOC container for DI

I have used Jpa and Hibernate for data persistence layer.

I have implemented basic unit test and have included logging for debugging purpose

NOTE: I have used H2 database so GET or PUT api won't work unless you save a employee first.

The first API allows the client the get employee objects by property id where the request may look like this.

GET /employee/?id=foo
The response should look like this.

{
  "id": "977e3f5b-6a70-4862-9ff8-96af4477272a",
  "name": "cookie bars",
  "role": "bar"
}

The second API allows the client to employee a user object with a request like this.

POST /employee/
{
  "id": "977e3f5b-6a70-4862-9ff8-96af4477272a",
  "name": "cookie bars",
  "role": "bar"
}
The third API allows the client to retrieve a user object given its ID

The second API allows the client to Save a already present employee object with a request like this.

PUT /employee/
{
  "id": "977e3f5b-6a70-4862-9ff8-96af4477272a",
  "name": "cookie bars",
  "role": "bar"
}
