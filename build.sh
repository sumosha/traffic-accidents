#!/usr/bin/env bash

echo "Starting the sbt build"

sbt clean assembly

echo "Starting the Docker build"

docker build -t sumosha/traffic_accidents:latest .

echo "Build complete."