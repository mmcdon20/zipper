package com.github.zipper

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

import Converter._

@RunWith(classOf[JUnitRunner])
class ConverterIntegrationTest extends Specification with ZipperData with ZipData {
  val out = "src\\test\\resources\\integration\\output\\"

  "Integration" should {

    "Be able to zip and then unzip" in {
      val name = out + "zthenu\\zip.zip"
      val zip = filesWithDirectory.makeZip(name)
      val unzip = zip.extractZip(out + "zthenu")
      zip.getName === name
      zip.getEntry("a.txt").getName === "a.txt"
      zip.getEntry("dir/d.txt").getName === "dir/d.txt"
      zip.getEntry("dir/dir2/e.txt").getName === "dir/dir2/e.txt"
      unzip.size === 5
      unzip.exists(_.getName == "e.txt") === true
    }

    "Be able to unzip and then zip" in {
      val name = out + "uthenz\\zip.zip"
      val unzip = zip2.extractZip(out + "uthenz")
      val zip = unzip.makeZip(name)
      zip.getName === name
      zip.getEntry("a.txt").getName === "a.txt"
      zip.getEntry("d.txt").getName === "d.txt"
      zip.getEntry("e.txt").getName === "e.txt"
      unzip.size === 5
      unzip.exists(_.getName == "e.txt") === true
    }
  }
}
