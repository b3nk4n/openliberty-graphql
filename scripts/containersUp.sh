#!/bin/bash

NETWORK=openliberty-graphql

docker network create $NETWORK

docker run -d \
  -p 9080:9080 \
  --network=$NETWORK \
  --name=system-service \
  --hostname=system \
  --rm \
  system-service:1.0-SNAPSHOT &

docker run -d \
  -p 9081:9081 \
  --network=$NETWORK \
  --name=frontend-service \
  --rm \
  frontend-service:1.0-SNAPSHOT &

wait
