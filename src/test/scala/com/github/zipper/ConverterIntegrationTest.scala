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
      val name = out + "zip then unzip\\zip.zip"
      val zip = filesWithDirectory.makeZip(name)
      val unzip = zip.extractZip(out + "zip then unzip")
      zip.getName === name
      zip.getEntry("a.txt").getName === "a.txt"
      zip.getEntry("dir/d.txt").getName === "dir/d.txt"
      zip.getEntry("dir/dir2/e.txt").getName === "dir/dir2/e.txt"
      unzip.size === 4
      unzip.exists(_.getName == "dir") === true
    }

    "Be able to unzip and then zip" in {
      val name = out + "unzip then zip\\zip.zip"
      val unzip = zip2.extractZip(out + "unzip then zip")
      val zip = unzip.makeZip(name)
      zip.getName === name
      zip.getEntry("a.txt").getName === "a.txt"
      zip.getEntry("dir/d.txt").getName === "dir/d.txt"
      zip.getEntry("dir/dir2/e.txt").getName === "dir/dir2/e.txt"
      unzip.size === 4
      unzip.exists(_.getName == "dir") === true
    }

    "Be able to unzip and then zip and then unzip" in {
      val name = out + "unzip then zip then unzip\\zip.zip"
      val unzip = zip2.extractZip(out + "unzip then zip then unzip")
      val zip = unzip.makeZip(name)
      val unzip2 = zip.extractZip(out + "unzip then zip then unzip\\unzip2")
      zip.getName === name
      zip.getEntry("a.txt").getName === "a.txt"
      zip.getEntry("dir/d.txt").getName === "dir/d.txt"
      zip.getEntry("dir/dir2/e.txt").getName === "dir/dir2/e.txt"
      unzip.size === 4
      unzip.exists(_.getName == "dir") === true
      unzip2.size === 4
      unzip2.exists(_.getName == "dir") === true
    }
  }
}
