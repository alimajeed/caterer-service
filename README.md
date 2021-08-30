# Caterer API 

Caterer API using Spring Boot.

- [x] Spring Boot App (No configuration required)
- [x] Save caterer
- [x] Get caterer by id
- [x] Get caterers by name (multiple possible because name can repeat)
- [x] Get caterers by city
- [x] Mongo DB
- [x] Kafka Message Broker
- [x] Pagination support
- [x] HATEOS Implemented for discoverable API
- [x] Unit Testing (Junit, Mockito)
- [x] Consumes/Produces Json By default (spring)
- [x] Swagger Documentation
- [x] Logging
- [x] Exception Handling (ControllerAdvice)
- [x] Caching (Default Map used)
- [x] Request payload validation
- [x] Docker Container
- [x] Docker Compose
- [x] Kubernetes Deployment Scripts

## Package
mvn clean package

## Start
java -jar target/signupservice-0.0.1-SNAPSHOT.jar

## Sample requests
### Postman
#### Save Caterer (POST)
http://localhost:8090/api/v1/caterers/save

{
    "name": "test1",
    "address": {
        "cityName": "Lahore",
        "streetAddress": "269-A"
    },
    "capacity": {
        "minGuestNo": 2,
        "maxGuestNo": 7
    },
    "contact" : {
        "email" : "test@tst.com",
        "mobileNumber" : "123123",
        "phoneNumber" : "3212312"
    }
}

#### Get Caterer By Id (GET)
http://localhost:8090/api/v1/caterers/id/612cfd590ed15c5b5d15d1a6

#### Get Caterers By Name (GET)
http://localhost:8090/api/v1/caterers/name/Cassidy Pitts

#### Get Caterers By City (GET)
http://localhost:8090/api/v1/caterers/city/Stockholm

### CURL (see later)
```
curl -d "{\"firstName\": \"admin\",\"lastName\": \"admin\", \"email\":\"admin@admin.com\", \"password\":\"password\"}" -H "Content-Type:application/json" -X POST http://localhost:8080/signup/
```

## Mongo Database UI
http://localhost:8081/

## Swagger Documentation
http://localhost:8080/swagger-ui.html

## Docker
#### Build
docker build -t alimjd/caterer-service-image .

#### Run
docker compose -f docker-compose.yaml up -d

## Kubernetes
#### Minikube
minikube start

#### Minikube UI
minikube dashboard

#### Kubernetes Services And Deployments
kubectl apply -f data-persistentvolumeclaim.yaml

kubectl apply -f mongodb-service.yaml,zookeeper-service.yaml,kafka-service.yaml,caterer-service.yaml

kubectl apply -f mongodb-deployment.yaml,zookeeper-deployment.yaml

kubectl apply -f kafka-deployment.yaml

kubectl apply -f caterer-deployment.yaml

#### Run
minikube service <service-name>
