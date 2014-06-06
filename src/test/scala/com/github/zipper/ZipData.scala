package com.github.zipper

import java.util.zip.ZipFile

trait ZipData {
  val dir2 = "src\\test\\resources\\zip\\"
  val out2 = dir2 + "output\\"
  val zip1 = new ZipFile(dir2 + "zip1.zip")
  val zip2 = new ZipFile(dir2 + "zip2.zip")
}
