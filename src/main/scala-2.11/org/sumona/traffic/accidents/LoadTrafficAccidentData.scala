package org.sumona.traffic.accidents

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}


object LoadTrafficAccidentData {

  def main(args: Array[String]): Unit = {

    val config = Config.parser.parse(args, Config()).getOrElse(throw new IllegalArgumentException("Could not parse the arguments. See above for usage statement"))

    println(s"Running job with the following configs: ${config}") //TODO: use a real logger

    implicit val sparkSession = SparkSession.builder().master("local").appName("Load Data").getOrCreate()

    val raw_df = sparkSession.read.option("header", "true").csv(config.inputPath)
    val cleaned = cleanColumnNames(raw_df)
    val toWrite = {
      config.filterDate match {
        case "from-beginning" =>  cleaned
        case _ => cleaned.where(s"${convertSpaceToUnderscore(config.filterColumnName)} >= '${config.filterDate}'")
      }
    }

    val outputFileName = FileName(config.inputPath).extractNameFromPath.csvToParquetExtension

    toWrite.write.mode(SaveMode.Append).parquet(s"/tmp/output/${outputFileName.name}")
  }

  def convertSpaceToUnderscore(name: String) = name.replaceAll("\\s+", "_")

  //TODO: this can be moved to a utility and tested independently
  def cleanColumnNames(df: DataFrame): DataFrame = {
    var cleaned = df
    df.columns.map(colName => {
      cleaned = cleaned.withColumnRenamed(colName, convertSpaceToUnderscore(colName))
    })
    cleaned
  }


}
