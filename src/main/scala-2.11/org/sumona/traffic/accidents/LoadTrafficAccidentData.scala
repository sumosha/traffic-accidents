package org.sumona.traffic.accidents

import java.time
import java.time.LocalDate
import java.time.format.DateTimeParseException
import java.util.logging.LogManager

import org.apache.spark.sql.{DataFrame, SparkSession}

import scala.util.{Failure, Success, Try}

/**
  * Created by Sumona on 9/16/2017.
  */
object LoadTrafficAccidentData {

  def main(args: Array[String]): Unit = {

    val config = Config.parser.parse(args, Config()).getOrElse(throw new IllegalArgumentException("Could not parse the arguments. See above for usage statement"))

    println(s"Running job with the following configs: ${config}") //TODO: use a real logger

    implicit val sparkSession = SparkSession.builder().master("local").appName("Load Data").getOrCreate()

    val raw_df = sparkSession.read.option("header", "true").csv(config.inputPath)
    val cleaned = cleanColumnNames(raw_df)
    val toWrite = {
      if (config.filterDate.equals("from-beginning")) {
        cleaned
      }
      else {
        val filtered = cleaned.where(s"${convertSpaceToUnderscore(config.filterColumnName)} >= '${config.filterDate}'")
        filtered.show(false)
        filtered
      }
    }

    sparkSession
    toWrite.write.parquet("hdfs://localhost:8000/somestuff")
  }

  def convertSpaceToUnderscore(name: String) = name.replaceAll("\\s+", "_")

  def cleanColumnNames(df: DataFrame): DataFrame = {
    var cleaned = df
    df.columns.map(colName => {
      cleaned = cleaned.withColumnRenamed(colName, convertSpaceToUnderscore(colName))
    })
    cleaned
  }


}
