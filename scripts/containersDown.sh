#!/bin/bash

NETWORK=openliberty-graphql

docker stop frontend-service system-service

docker network rm $NETWORK
