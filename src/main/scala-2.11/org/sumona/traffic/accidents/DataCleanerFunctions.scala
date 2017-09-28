package org.sumona.traffic.accidents

import org.apache.spark.sql.DataFrame

object DataCleanerFunctions {

  def convertSpaceToUnderscore(name: String) = name.replaceAll("\\s+", "_")

  def cleanColumnNames(df: DataFrame): DataFrame = {
    var cleaned = df
    df.columns.map(colName => {
      cleaned = cleaned.withColumnRenamed(colName, DataCleanerFunctions.convertSpaceToUnderscore(colName))
    })
    cleaned
  }
}
