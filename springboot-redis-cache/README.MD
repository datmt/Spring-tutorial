# Spring Boot Redis cache tutorial



## What are we building?
This post shows you how to use Redis as a cache in Spring boot application. I will setup three components:

* Redis cache
* Spring boot application
* A pseudo data store containing some data that changes over time. We are going to use a Map for this.

When application users make calls to the application, the application will check if data is available in the Redis cache. If yes, the data will be returned to the application users.

In case there is no data in the Redis cache, the application will query the data store to get the data and return it to the users, also stores that data in Redis cache.

Full tutorial is available here [Spring boot redis cache tutorial](https://datmt.com/backend/java/spring/configure-redis-cache-with-spring-boot-w-examples/)
