# traffic-accidents

This is a simple Spark application to read in data and output to a query-able store. It runs on a single-node Docker image with Spark and stores data to the local file system only. This is useful for demo purposes only.

### Requirements to build
* sbt
* docker

### How to Create and Run the Docker image
* Execute the build.sh script at the root of this project. This script runs an sbt build and a subsequent docker build. It tags the image as sumosha/traffic_accidents:latest
* `docker run -it sumosha/traffic_accidents:latest `
  * This will launch a spark-shell session, where we can create tables, query, and aggegrate data directly in here. See below...
  
### How to query Data
* Since we are running in a non-persistent Spark environment, the data needs to be preloaded as tables using the shell. Here's how:
  
``` scala
val df1 = spark.read.parquet("/tmp/output/311_service_requests.parquet")
df1.createOrReplaceTempView("service_requests")
val df2 = spark.read.parquet("/tmp/output/traffic_accidents.parquet") 
df2.createOrReplaceTempView("accidents")
```

* Now we can query using sql (spark SQL) against tables service_requests and accidents. To verify this:
``` scala
spark.sql("select * from service_requests").show()
spark.sql("select * from accidents").show()
```

* Spark SQL reference [here](https://docs.databricks.com/spark/latest/spark-sql/index.html) -- it's just sql really

### Example Queries
These can be located in the queries folder.

### Thoughts for a real product
* In reality, this problem can be abstracted out to a more generic input --> output pattern, perhaps even with custom functions to perform specific or generic transformations. This toy app can be made more configurable for a wide variety of uses.
* For demonstration only, the docker image contains the output of the job run on the local disk. In the real world, I would output the data to an S3 or HDFS store somewhere.
  * This can easily be changed to store to these systems using the URI pattern of s3a:// or hdfs://
  * I would use Hive or AWS Athena to query the output.
* This should run on a Spark cluster when dealing with larger amounts of data. How that is set up and launched depends on the existing infrastructure being used.


