package com.github.zipper

import java.io.BufferedReader
import scala.io.Source
import java.util.zip.ZipOutputStream

trait IO {
  protected def getReader(path: String): BufferedReader =
    Source.fromFile(path).bufferedReader()

  protected def writeBytes(in: BufferedReader, out: ZipOutputStream): Unit =
    readBytes(in).takeWhile(_ > -1).foreach(out.write)

  protected def readBytes(reader: BufferedReader): Stream[Int] =
    reader.read() #:: readBytes(reader)
}
