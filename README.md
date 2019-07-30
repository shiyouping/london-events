# London Events

This system is a simplified version of [eventful](https://www.eventful.com) but with additional information, e.g. weather forecast for events, and has London upcoming events, concerts, festivals, movies and more. You can check events sorted by date in different categories. This system is composed of a back end application based on [Spring Boot](https://spring.io/projects/spring-boot), and a single page web application based on [React](https://reactjs.org/) and [Bootstrap](https://getbootstrap.com/).

![Web Screenshot!](/doc/web-screenshot.png)

## 1. Server Architecture
This server application is mainly built with Spring MVC. Its RESTful APIs are intended to be consumed by any internal and external clients(server, web, iOS and Android applications), so [CORS](https://en.wikipedia.org/wiki/Cross-origin_resource_sharing) is enabled by default. Because this is a public application, there is no session and no security control. This server architecture has four layers, i.e. Controller Layer, Service Layer and Third Party Client Layer. 

![Server Architecture!](/doc/server-architecture.jpeg)

### 1.1. Controller Layer
The Controller layer defines external endpoints based on REST/HTTP. The HTTP requests are validated before processing, and requests and responses are converted between JSON and Java Object. When data goes to or from Service layer, they are converted too by using different DTO and Document mappers so that sensitive information is removed before returning to clients. The system exceptions are caught by this layer, and corresponding error responses are given by different exception handlers.

### 1.2. Service Layer
The Service layer is responsible for performing specific business logic which cannot be done on Controller layer. This layer has the full access to the underlying data and is only accessible internally. Data passed in from Controller layer could be further validated, and transaction and cache is available on this layer.

### 1.3. Third Party Client Layer

## 2. Web Architecture
![Web Architecture!](/doc/web-architecture.jpeg)

### 2.1. Overview
This application is designed with the `component-oriented` concept, and composed of `UI Layer`, `Middle Layer` and `Non-UI Layer`.

| Layer        | React Based | Automation Test |
| ------------ | :---------: | --------------: |
| UI Layer     |     Yes     |       Difficult |
| Middle Layer |   Partial   |          Median |
| Non-UI Layer |     No      |            Easy |

#### 2.1.1. UI Layer
This layer relies heavily on HTML, CSS and React. It is where end users interact with this application, so it is not that easy to apply unit testing and other automation testing to this layer.

#### 2.1.2. Middle Layer
This layer is where this application transits from UI Layer to Non-UI Layer. Therefore, there may be UI and Non-UI code mixed in one component.

#### 2.1.3. Non-UI Layer
This layer is a `pure JavaScript` layer and isolated from React. The automation testing, e.g. unit testing will be applied to this layer to facilitate CI/CD.

### 2.2. Hierarchy
From the architecture diagram, UI Layer is based on Middle Layer and Non-UI Layer, and Middle Layer relies on Non-UI Layer. Each layer is composed of different components respectively.

Components on the same level are marked with the same colors. The components are visible to those components which are on the same or higher levels. That means components are able to call other components which are on the same or lower levels. For example, REST Service and WebSocket Service are allowed to call each other, and both of them are legal to use SharedStore. However, REST Service will never utilize React Framework or Application Level Service.

### 2.3. Model & View
Views on UI Layer are `ONLY` allowed to update virtual DOM via REST Service, and SharedStore, i.e. views change data, and data being changed will cause UI to refresh itself.
