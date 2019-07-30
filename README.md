# London Events

This system is a simplified version of [eventful](https://www.eventful.com) but with additional information, e.g. weather forecast for events, and has London upcoming events, concerts, festivals, movies and more. You can check events sorted by date in different categories. This system is composed of a back end application based on [Spring Boot](https://spring.io/projects/spring-boot), and a single page web application based on [React](https://reactjs.org/) and [Bootstrap](https://getbootstrap.com/).

![Web Screenshot!](/doc/web-screenshot.png)

## 1. Server Application
### 1.1. Server Architecture
This server application is mainly built with Spring MVC. Its RESTful APIs are intended to be consumed by any internal and external clients(server, web, iOS and Android applications), so [CORS](https://en.wikipedia.org/wiki/Cross-origin_resource_sharing) is enabled by default. Because this is a public application, there is no session and no security control. This server architecture has three layers, i.e. `Controller Layer`, `Service Layer` and `Third Party Client Layer`. 

![Server Architecture!](/doc/server-architecture.jpeg)

#### 1.1.1. Controller Layer
The `Controller Layer` defines external RESTFul APIs based on HTTP. Requests and responses are converted between JSON and Java Object by using `Jackson Converter`. When data goes to or from `Service Layer`, they are converted too by using `DTOModel Mapper`s so that sensitive information is removed before returning to clients. The system exceptions are caught by this layer, and corresponding error responses are given by different `Exception Handler`s. Currently, only `GET` requests are supported. In the case that POST, PUT and other verbs need to be supported, Spring request validator can be easily enabled to validate request body. If security and session are necesary in the future, they can be integrated in this layer without difficulties.

#### 1.1.2. Service Layer
The `Service Layer` is responsible for performing specific business logic which cannot be done in `Controller Layer`. This layer has the full access to the underlying data and is only accessible internally. Data passed in from `Controller Layer` could be further validated. Schedulers and cache management are available on this layer.

#### 1.1.3. Third Party Client Layer
This layer defines clients for third party APIs. `ONLY` useful information from external APIs is mapped to internal Java Objects, and further processed by `Service Layer`.

### 1.2. Cache Management
To retrieve data from third party APIs is slow, so cache is applied at `Service Layer` to accelerate query. However, event and weather data will be updated on third party side. To solve it, cache eviction is scheduled at a fixed rate of 1 minute, given the assumption that event and weather do not change very frequently.

### 1.3. Different Levels of Testing
To guarantee the code quality and facilitate CI/CD in the future, different levels of test cases are written for different classes. Currently, the following testing is covered by using BDD methodology:
- Automated unit testing with vanilla Java
- Automated unit testing with [Mockito](https://site.mockito.org/) framework 
- Automated integration testing with [WireMock](http://wiremock.org/) and Spring Boot Test frameworks
- Manual system testing and acceptance testing

### 1.3. How to Run
In order to run this server application in development environment, [Java](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Maven](https://www.apache.org/) need to be installed first. Then execute the following command to start:
````java
$ mvn spring-boot:run
````

## 2. Web Application
### 2.1. Web Architecture

![Web Architecture!](/doc/web-architecture.jpeg)

This application is designed with the `component-oriented` concept, and composed of `UI Layer`, `Middle Layer` and `Non-UI Layer`.

| Layer        | React Based | Automation Test |
| ------------ | :---------: | --------------: |
| UI Layer     |     Yes     |       Difficult |
| Middle Layer |   Partial   |          Median |
| Non-UI Layer |     No      |            Easy |

#### 2.1.1. UI Layer
This layer relies heavily on HTML, CSS and React. It is where general users interact with this application, so it is not that easy to apply unit testing and other automation testing to this layer.

#### 2.1.2. Middle Layer
This layer is where this application transits from `UI Layer` to `Non-UI Layer`. Therefore, there may be UI and Non-UI code mixed in one component.

#### 2.1.3. Non-UI Layer
This layer is a `pure JavaScript` layer and isolated from React. The automation testing, e.g. unit testing will be applied to this layer to facilitate CI/CD.

#### 2.1.4. Hierarchy
From the architecture diagram, `UI Layer` is based on `Middle Layer` and `Non-UI Layer`, and `Middle Layer` relies on `Non-UI Layer`. Each layer is composed of different components respectively.

Components on the same level are marked with the same colors. The components are visible to those components which are on the same or higher levels. That means components are able to call other components which are on the same or lower levels. For example, `SystemService` and `NotificationService` are allowed to call each other, and both of them are legal to use `EventEmitter`. However, `REST Service` will never utilize `React Framework` or `Application Level Service`.

### 2.2. How to Run
In order to run this web application in development environment, [Node.js](https://nodejs.org/en/) needs to be installed first. Then execute the following command to start:
````java
$ npm install
$ npm start
```` 