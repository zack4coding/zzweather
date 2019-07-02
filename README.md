## zzweather
This is a web application to  display current weather which implement by [Spring Boot/Webflux](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux) and [Vue](https://vuejs.org/). 
<br>
[The fronted project](https://github.com/zack4coding/zzweather-vue)

Start it by <br>
Step 1:
```
$ cd /path/to/zzweather
$ mvn clean install
```
Step 2
```
$ cd ./zzweather-webflux
$ mvn spring-boot:run

#### Modules of zzweather

- #### zzweather-service-api <br>
     This module is divided out for other modules reuse of the APIs and DTO(data transfer object), especially for microservice such as Spring Cloud.
- #### zzweather-service-provider <br>
     This module provide the mainly service by register task to scheduler module that refresh the weather data periodically to keep our system updated.<br>
     Then apply Factory & Strategy pattern to manage various source API of weather.
- #### zzweather-scheduler
     Interval task executor which duration config by profile and make efforts in provide task execution for every specific field of the weather data of every specific area.
- #### zzweather-webfux
     Publish lasted real-time weather data to client after request the sse api.
- #### zzweather-exception
     Global unified error and exception handle module,for the future, it will make issues being investigated in an elegant way.
#### System Architecture 
![architecture design](https://github.com/zack4coding/zzweather/blob/master/weather-service-architecture.png)

