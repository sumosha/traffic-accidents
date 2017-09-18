package org.sumona.traffic.accidents

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by Sumona on 9/16/2017.
  */
class TestConfig extends FlatSpec with Matchers {


  it should "parse inputs with fully qualifed arg name" in {
    val config: Option[Config] = Config.parser.parse(Seq("--inputPath", "path/to/file", "--filterDate", "2017-06-01", "-c", "whatever"), Config())
    config should be (Some(Config(inputPath = "path/to/file", filterDate = "2017-06-01", filterColumnName = "whatever")))
  }

  it should "be None if date format is not valid" in {
    val config = Config.parser.parse(Seq("-i", "path/to/file", "--filterDate", "2017santoheusanoetuh", "-c", "whatever"), Config())
    config should be (None)
  }

  it should "accept date as 'from-beginning'" in {
    val config = Config.parser.parse(Seq("-i", "path/to/file", "--filterDate", "from-beginning", "-c", "whatever"), Config())
    config should be (Some(Config(inputPath = "path/to/file", filterDate = "from-beginning", filterColumnName = "whatever")))
  }
}
