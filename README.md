# Theater
This is theater project to get movie schedules for the day like movie name, time, ticket price and show sequence for the given day. It also has API call to reserve the tickets.

This application developed using SpringBoot Microservices framework
Rest API has 3 end points (Assuming this service running in Tomcat with default port number: 8080
1. To get movie schedules in json format: http://localhost:8080/movieschedules
2. To view the movie schedules in the simple text readble format: http://localhost:8080/formattedmovieschedules
3. To make resrvation for the movie for particluar show: http://localhost:8080/reserve?customerName=Supriya&showNum=1&ticketCount=3


