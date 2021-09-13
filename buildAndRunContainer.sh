#!/bin/bash

docker build --tag=aparito-tech-assignment-wildfly .
docker run -it -p 8080:8080 aparito-tech-assignment-wildfly
