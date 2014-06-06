package com.github.zipper

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

import Converter._

@RunWith(classOf[JUnitRunner])
class ConverterZipTest extends Specification with ZipData {
  "Zip" should {
    "have zip files" in {
      zip1.getEntry("a.txt").getName === "a.txt"
      zip2.getEntry("dir/d.txt").getName === "dir/d.txt"
    }

    "Decompress a zip without folders" in {
      val unzip = zip1.extractZip(out2 + "zip1")
      unzip.size === 3
      unzip.exists(_.getName == "a.txt") === true
    }

    "Decompress a zip with folders" in {
      val unzip = zip2.extractZip(out2 + "zip2")
      unzip.size === 4
      unzip.exists(_.getName == "dir") === true
    }
  }
}
