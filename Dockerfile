FROM epahomov/docker-spark:spark_2.1_hadoop_2.7

COPY target/scala-2.11/traffic-accidents-assembly-1.0.jar /tmp/
COPY launch.sh /tmp/launch.sh
COPY source-data/* /tmp/source-data/

RUN  /tmp/launch.sh --inputPath file:///tmp/source-data/traffic_accidents.csv --filterDate from-beginning

CMD ["/spark/bin/spark-shell", "--master local[4]"]
