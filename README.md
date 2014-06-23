# zipper

scala library for creating and extracting collections of files as zip archives

### Getting Started

To get started simply import the implicit conversions.

```scala
import com.github.zipper.Converter._
import java.io.File
import java.util.zip.ZipFile
```

### Create a zip file

Any collection of java.io.File that can be foreached over can be written to a zip file, including parellel collections.
If the collection includes a directory, the contents of the directory will also be written to the zip file.
makeZip takes a name for the zip file and returns a java.util.zip.ZipFile.

```scala
val files: List[File] = new File("a.txt") ::
                        new File("b.txt") ::
                        new File("c.txt") ::
                        new File("dir") :: Nil

val zip: ZipFile = files.makeZip("zip_example.zip")                        
```

Using Java collections such as ArrayList is also possible with the JavaConversions package.

```scala
import java.util.ArrayList
import scala.collection.JavaConversions._

val files: ArrayList[File] = new ArrayList[File]()

files.add(new File("a.txt"))
files.add(new File("b.txt"))
files.add(new File("c.txt"))
files.add(new File("dir"))

val zip: ZipFile = files.makeZip("zip_example.zip")                        
```

### Extract a zip file

Extracting a zip takes a destination path and returns a List[File].

```scala
val unzip: List[File] = zip.extractZip("output")
```
