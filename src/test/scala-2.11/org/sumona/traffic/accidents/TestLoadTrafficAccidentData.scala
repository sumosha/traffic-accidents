package org.sumona.traffic.accidents

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by Sumona on 9/16/2017.
  */
class TestLoadTrafficAccidentData extends FlatSpec with Matchers {

  ignore should "work with from-beginning read" in {
    LoadTrafficAccidentData.main(Array("--inputPath", "source-data/traffic_accidents.csv", "--filterDate", "from-beginning", "--filterColumnName", ""))
  }

  ignore should "work with filter date" in {
    LoadTrafficAccidentData.main(Array("--inputPath", "source-data/traffic_accidents.csv", "--filterDate", "2013-05-03", "--filterColumnName", "FIRST_OCCURRENCE_DATE"))
  }

  "convertSpaceToUnderscore" should "replace spaces" in {
    LoadTrafficAccidentData.convertSpaceToUnderscore("some   annoying text") should be ("some_annoying_text")
    LoadTrafficAccidentData.convertSpaceToUnderscore("nospaces") should be ("nospaces")
  }
}
