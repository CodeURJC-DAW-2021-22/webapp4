#!/bin/bash

docker build -f Dockerfile -t $1 .

docker tag $1 registry.heroku.com/$1/web

heroku git:remote -a $1

heroku container:login

docker push registry.heroku.com/$1/web

heroku container:release web -a $1

heroku logs --tail -a $1
