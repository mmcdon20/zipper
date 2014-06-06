package com.github.zipper

import java.io.{InputStream, FileOutputStream, File}
import java.util.zip.{ZipEntry, ZipOutputStream, ZipFile}

object Converter {
  type FileCollection = { def foreach [T] (item: File => T): Unit }
  implicit def toZipper [A <% FileCollection] (collection: A) = new Zipper(collection)
  implicit def toZip (zipFile: ZipFile) = new Zip(zipFile.getName)

  class Zipper [A <% FileCollection] (collection: A) extends Lockable with IO {

    def makeZip(name: String): ZipFile = {
      new File(name).getParentFile.mkdirs()
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

  class Zip(name: String) extends ZipFile(name) {
    def extractZip(destination: String): List[File] = {
      val e = entries
      var files: List[File] = Nil
      while (e.hasMoreElements) {
        files = extractEntry(e.nextElement, destination) :: files
      }
      files
    }

    private def extractEntry(entry: ZipEntry, destination: String): File = {
      val fileName = destination + "\\" + entry.getName
      val file = new File(fileName)
      file.getParentFile.mkdirs()

      val in: InputStream = getInputStream(entry)
      val out: FileOutputStream = new FileOutputStream(fileName)
      val buffer = Array.ofDim[Byte](1024*4)
      val size = entry.getSize
      var count = 0L
      var n = in.read(buffer)

      while (n != -1 && count < size) {
        out.write(buffer, 0, n)
        count += n
        n = in.read(buffer)
      }

      file
    }
  }
}
