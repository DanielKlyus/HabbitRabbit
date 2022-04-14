#!/bin/sh
docker run -d --name habbit-rabbit-db -p 5434:5432 -e POSTGRES_PASSWORD=123123 -e POSTGRES_USER=superadmin -e POSTGRES_DB=habbit-rabbit-db postgres:alpine
sleep 2
read -n 1 -s -r -p "Press any key to continue"