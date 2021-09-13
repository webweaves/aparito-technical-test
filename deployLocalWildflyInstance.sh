#!/bin/bash

mvn clean install wildfly:deploy -pl web-frontend -pl primenumbers-rest-api
