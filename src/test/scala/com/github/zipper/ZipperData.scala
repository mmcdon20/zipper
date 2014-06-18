package com.github.zipper

import java.io.File

trait ZipperData {
  val dir1 = "src\\test\\resources\\zipper\\"
  val out1 = dir1 + "output\\"
  val file1 = new File(dir1 + "a.txt")
  val file2 = new File(dir1 + "b.txt")
  val file3 = new File(dir1 + "c.txt")
  val direc = new File(dir1 + "dir")
  val files = file1 :: file2 :: file3 :: Nil
  val filesWithDirectory = direc :: files

  val arrayList = new java.util.ArrayList[java.io.File]()
  arrayList.add(file1)
  arrayList.add(file2)
  arrayList.add(file3)
  arrayList.add(direc)
}
