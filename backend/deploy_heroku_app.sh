#!/bin/bash

#mvn spring-boot:build-image -Dspring-boot.build-image.imageName=registry.heroku.com/$1/web

docker build -f heroku.Dockerfile -t $1 .

docker push registry.heroku.com/$1/web

heroku container:release web -a $1

heroku logs --tail -a $1
