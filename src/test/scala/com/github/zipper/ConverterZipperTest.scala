package com.github.zipper

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

import Converter._

@RunWith(classOf[JUnitRunner])
class ConverterZipperTest extends Specification with ZipperData {
  "Zipper" should {

    "Compress a List[java.io.File] to zip" in {
      val name = out + "basic list.zip"
      val zip = files.makeZip(name)
      zip.getName === name
      zip.getEntry("a.txt").getName === "a.txt"
      zip.getEntry("b.txt").getName === "b.txt"
      zip.getEntry("c.txt").getName === "c.txt"
    }

    "Compress a List[java.io.File] with a directory to zip" in {
      val name = out + "list with directory.zip"
      val zip = filesWithDirectory.makeZip(name)
      zip.getName === name
      zip.getEntry("a.txt").getName === "a.txt"
      zip.getEntry("dir/d.txt").getName === "dir/d.txt"
      zip.getEntry("dir/dir2/e.txt").getName === "dir/dir2/e.txt"
    }

    "Compress a parallel List[java.io.File] to zip" in {
      val name = out + "parallel list.zip"
      val zip = files.par.makeZip(name)
      zip.getName === name
      zip.getEntry("a.txt").getName === "a.txt"
      zip.getEntry("b.txt").getName === "b.txt"
      zip.getEntry("c.txt").getName === "c.txt"
    }

    "Compress a parallel List[java.io.File] with a directory to zip" in {
      val name = out + "parallel list with directory.zip"
      val zip = filesWithDirectory.par.makeZip(name)
      zip.getName === name
      zip.getEntry("a.txt").getName === "a.txt"
      zip.getEntry("dir/d.txt").getName === "dir/d.txt"
      zip.getEntry("dir/dir2/e.txt").getName === "dir/dir2/e.txt"
    }

    "Compress a Set[java.io.File] to zip" in {
      val name = out + "set.zip"
      val zip = filesWithDirectory.toSet.makeZip(name)
      zip.getName === name
      zip.getEntry("a.txt").getName === "a.txt"
      zip.getEntry("dir/d.txt").getName === "dir/d.txt"
      zip.getEntry("dir/dir2/e.txt").getName === "dir/dir2/e.txt"
    }

    "Compress an Array[java.io.File] to zip" in {
      val name = out + "array.zip"
      val zip = filesWithDirectory.toArray.makeZip(name)
      zip.getName === name
      zip.getEntry("a.txt").getName === "a.txt"
      zip.getEntry("dir/d.txt").getName === "dir/d.txt"
      zip.getEntry("dir/dir2/e.txt").getName === "dir/dir2/e.txt"
    }

    "Compress a Stream[java.io.File] to zip" in {
      val name = out + "stream.zip"
      val zip = filesWithDirectory.toStream.makeZip(name)
      zip.getName === name
      zip.getEntry("a.txt").getName === "a.txt"
      zip.getEntry("dir/d.txt").getName === "dir/d.txt"
      zip.getEntry("dir/dir2/e.txt").getName === "dir/dir2/e.txt"
    }
  }
}
