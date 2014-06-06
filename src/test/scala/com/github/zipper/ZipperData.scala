package com.github.zipper

import java.io.File

trait ZipperData {
  val dir = "src\\test\\resources\\zipper\\"
  val out = dir + "output\\"
  val file1 = new File(dir + "a.txt")
  val file2 = new File(dir + "b.txt")
  val file3 = new File(dir + "c.txt")
  val direc = new File(dir + "dir")
  val files = file1 :: file2 :: file3 :: Nil
  val filesWithDirectory = direc :: files
}
