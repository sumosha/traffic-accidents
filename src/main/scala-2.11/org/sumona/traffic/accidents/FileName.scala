package org.sumona.traffic.accidents

case class FileName(name: String) {
  def extractNameFromPath: FileName = FileName(name.split("/").reverse.head)
  def csvToParquetExtension: FileName = FileName(name.replace(".csv", ".parquet"))
}
