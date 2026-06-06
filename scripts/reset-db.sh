#!/usr/bin/env bash

set -e

echo "Resetting MongoDB database: newdb"

docker exec nfs-java-c1-mongo mongosh \
  -u admin \
  -p pwd12345 \
  --authenticationDatabase admin \
  --eval "db.getSiblingDB('newdb').courses.drop()"

echo "Importing course seed data"

docker cp seed/courses.json nfs-java-c1-mongo:/tmp/courses.json

docker exec nfs-java-c1-mongo mongoimport \
  -u admin \
  -p pwd12345 \
  --authenticationDatabase admin \
  --db newdb \
  --collection courses \
  --file /tmp/courses.json \
  --jsonArray

echo "Seed complete"