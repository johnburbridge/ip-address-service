IP Address Service
------------------
A simple service that returns the server's ip address.

Building:
`./gradlew bootJar docker`

Running from Gradle:
`./gradlew bootRun`

Or from docker:
`docker run -t gcr.io/spring-sandbox-219017/ip-address-server -p 8080:8080`

You shold be able to hit the following endpoints:
```
curl 172.17.0.2:8080
curl 172.17.0.2:8080/interfaces
curl 172.17.0.2:8080/actuator
curl 172.17.0.2:8080/actuator/info
curl 172.17.0.2:8080/actuator/health
curl 172.17.0.2:8080/actuator/metrics
curl 172.17.0.2:8080/actuator/metrics/system.load.average.1m

# And this one kills the server (to simulate crashes)
curl 172.17.0.2:8080/actuator/shutdown -X POST
```
