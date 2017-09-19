package org.sumona.traffic.accidents

import org.scalatest.{FlatSpec, Matchers}

class TestFileName extends FlatSpec with Matchers {

  "csvToParquetName" should "replace extension" in {
    FileName("input.csv").csvToParquetExtension.name should be ("input.parquet")
  }

  "extractNameFromPath" should "get fileName only" in {
    FileName("/tmp/input.csv").extractNameFromPath.name should be ("input.csv")
  }

  "given path with no slashes, extractNameFromPath" should "keep the name" in {
    FileName("input.csv").extractNameFromPath.name should be ("input.csv")
  }

  "FileName" should "chain" in {
    FileName("/tmp/input.csv").extractNameFromPath.csvToParquetExtension.name should be ("input.parquet")
  }

}
