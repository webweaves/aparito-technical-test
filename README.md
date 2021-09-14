# Technical Test – Christian Weaves – September 2021

## The solution

This project comprises of the following components

* Two maven component projects (web-frontent and primenumbers-rest-api)
* Issue tracking documentation
* Code coverage reports
* SCM and a tagged release version
* Unit tests
* Local and container deployment options

## Pre-requistes

* JDK11
* Maven
* Wildfly (see running options below)

All CLI commands mentioned in this document are run from the project root (aparito-technical-test).

## Getting started

Clone the git repository with the following command:
git clone https://github.com/webweaves/aparito-technical-test.git 

Change directory to aparito-technical-test with **cd aparito-technical-test**

## Deployment
There are two options for deployment 
* To deploy locally you will need an install of wildfly running, download, install and startup [Download here](https://www.wildfly.org/downloads/)
When wildfly is running you can run the script **deployLocalWildflyInstance.sh** from the command line, this will build and deploy the application.
After deployment point browser to: http://localhost:8080/PrimeNumbers/

* Run with docker
Ensure wildfly is not running on the target machine.
Build, install and run the docker container with the script **buildAndRunContainer.sh**
After deployment point browser to: http://localhost:8080/PrimeNumbers/

## Issue tracking
Although only a small project it is vital to track all tasks using Jira. Not only does highlight all outstading work but also helps maintain focus.

All source code commits where cross referenced with the Jira issue number (where applicable).

[See Jira documentation here](documentation/jira/AparitoTechTestJiraIssues.doc)

## Code Test coverage
Each time a project is built with maven code coverage reports are updated in the web-frontend/target/site/jacoco & primenumbers-rest-api/target/site/jacoco folders.

Follow the links below for detailed unit test coverage:

[Project: web-frontend code test coverage](documentation/codeTestCoverage/primenumbers-rest-api/index.html)
[Project: primenumbers-rest-api](documentation/codeTestCoverage/web-frontend/index.html)

## Build and deploy with maven:

### Installing with maven
mvn install


## Build and deploy individual projects with maven:

### Prime numbers REST API
mvn clean install wildfly:deploy -pl primenumbers-rest-api

Example call:
http://localhost:8080/primenumbers-rest-api/resources/generate-prime-numbers/999

### Frontend component
mvn clean install wildfly:deploy -pl web-frontend

This web component has a dependency on the prime numbers deployment above

View deployment:
http://localhost:8080/PrimeNumbers/

If the prime numbers REST API is not deployed you will see the following in the server application logs: Error with REST request: HTTP 404 Not Found
