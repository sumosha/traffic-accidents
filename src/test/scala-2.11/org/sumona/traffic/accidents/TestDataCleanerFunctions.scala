package org.sumona.traffic.accidents

import org.apache.spark.sql.SparkSession
import org.scalatest.{FlatSpec, Matchers}

class TestDataCleanerFunctions extends FlatSpec with Matchers {

  "convertSpaceToUnderscore" should "replace spaces" in {
    DataCleanerFunctions.convertSpaceToUnderscore("some   annoying text") should be("some_annoying_text")
    DataCleanerFunctions.convertSpaceToUnderscore("nospaces") should be("nospaces")
  }

  "cleanColumnNames" should "rename the columns with underscores" in {
    val sparkSession = SparkSession.builder().master("local").appName(this.getClass.getName).getOrCreate()

    val df = sparkSession.read.csv("source-data/311_service_requests.csv").limit(5)
    val output = DataCleanerFunctions.cleanColumnNames(df)
    output.columns.forall(str => !str.contains(' ')) should be (true)
  }

}
