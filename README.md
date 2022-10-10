# Theater
This is theater project to get movie schedules for the day like movie name, time, ticket price and show sequence for the given day. It also has API call to reserve the tickets.

1. This application developed using SpringBoot Microservices Web MVC framework, Lombok and DevTools.
2. Rest API has 3 end points (Assuming this service running in Tomcat with default port number: 8080
   * To get movie schedules in json format: http://localhost:8080/movieschedules
   * To view the movie schedules in the simple text readble format: http://localhost:8080/formattedmovieschedules
   * To make resrvation for the movie for particluar show: http://localhost:8080/reserve?customerName=Supriya&showNum=1&ticketCount=3
      => 20% discount for the special movie (Need to setup movies with special code:1 to categorize as special movie)    
      =>  $3 discount for the movie showing 1st of the day
      => $2 discount for the movie showing 2nd of the day
      => Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
      => Any movies showing on 7th day of the month, you'll get 1$ discount
      => The only one biggest discount amount applied if it meets multiple discount rules;
3. Initial data set-up for movies and daily showing implemneted in application-dev.yml. This implementaion separates data set-up from the business functionality. Movies/Shows changes can be updated without touching underlying java code.
4. Implemneted exceptions using Global Exception Handler
5. Implemneted Junit test cases using JUnit 5 and Mockito. 
6. Analyzed test coverage using jacoco plugin and EclEmma Java Code Coverage
7. Implemneted Swagger API for documentaion and UI for user can test API calls
   * Here is the Swagger API URL: http://localhost:8080/swagger-ui.html
   Swagger UI has option to test all end points.
8. This application can also be validated with Postman to see the results. Following are the end points.
   * To get movie schedules in json format: http://localhost:8080/movieschedules
   * To view the movie schedules in the simple text readble format: http://localhost:8080/formattedmovieschedules
   * To make resrvation for the movie for particluar show: http://localhost:8080/reserve?customerName=Supriya&showNum=1&ticketCount=3
9. Implemneted logging using Slf4j
