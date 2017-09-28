package org.sumona.traffic.accidents

import java.time.LocalDate

import scopt.OptionParser

import scala.collection.immutable.Iterable
import scala.util.{Failure, Success, Try}

object Config {

  val parser = new OptionParser[Config]("generic-data-loader") {
    opt[String]('i', "inputPath").required().valueName("path/to/file/local/or/hdfs/or/s3").action( (x, c) => {
      c.copy(inputPath = x)
    }).text("Path to input data. This could be an s3 or hdfs location url")

    opt[String]('f', "filterDate").required().valueName("yyyy-MM-dd formatted date. Or 'from-beginning' to process all data.").action( (x, c) => {
      c.copy(filterDate = x)
    }).validate(date => {
      if (date.equals("from-beginning")
        || Try(LocalDate.parse(date)).isSuccess) success
      else failure("Could not parse date. Should be either 'from-beginning' or in yyyy-MM-dd format.")
    }).text("Start date of data to include. All data for dates equal to or later than this will be included.")

    opt[String]('c', "filterColumnName").valueName("path/to/file/local/or/hdfs/or/s3").action( (x, c) => {
      c.copy(filterColumnName = x)
    }).text("Column name of the data to be filtered on.")
  }
}

//inputPath=2017-06-01 format for inputs
case class Config(inputPath: String = "", filterDate:String="", filterColumnName:String="")
