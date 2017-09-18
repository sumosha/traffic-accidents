#!/bin bash

/spark/bin/spark-submit \
--class org.sumona.traffic.accidents.LoadTrafficAccidentData
--deploy-mode client \
/tmp/traffic-accidents-assembly-1.0.jar \
$@