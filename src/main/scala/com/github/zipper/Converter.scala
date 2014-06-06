package com.github.zipper

import java.io.{FileOutputStream, File}
import java.util.zip.{ZipEntry, ZipOutputStream, ZipFile}

object Converter {
  type FileCollection = { def foreach [T] (item: File => T): Unit }
  implicit def toZipper [A <% FileCollection] (collection: A) = new Zipper(collection)
  implicit def toZip (zipFile: ZipFile) = new Zip(zipFile.getName)

  class Zipper [A <% FileCollection] (collection: A) extends Lockable with IO {

    def makeZip(name: String): ZipFile = {
      val zip = new ZipOutputStream(new FileOutputStream(name))
      compressFiles(zip,collection)
      zip.close()
      new ZipFile(name)
    }

    private def compressFiles(zip: ZipOutputStream, files: FileCollection, dir: String = ""): Unit = {
      for (file <- files) {
        if (file.isDirectory) {
          compressFiles(zip,file.listFiles,s"$dir${file.getName}/")
        } else lockingOperation {
          compressFile(zip,file,dir)
        }
      }
    }

    private def compressFile(zip: ZipOutputStream, file: File, dir: String): Unit = {
      zip.putNextEntry(new ZipEntry(dir + file.getName))
      val in = getReader(file.getCanonicalPath)
      writeBytes(in,zip)
      in.close()
      zip.closeEntry()
    }
  }

  class Zip(name: String) {
    def extractZip(destination: String) = {
      ???
    }
  }
}
