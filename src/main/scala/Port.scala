/**
 * Ports the BijanKhan corpus format to the OpenNLP training format
 * 
 */
object Port {

  def main(args: Array[String]): Unit = {
    
    port("corpus/BijanKhan_original.txt","corpus/BijanKhan_opennlp.txt")
  }
  
  import java.io._
  def port(srcFile: String, targetFile:String): Unit = {
		  //  val writer = new FileWriter(targetFile)
    val Encoding = "UTF-8"  
    val fos = new FileOutputStream(targetFile)
    val writer = java.nio.channels.Channels.newWriter(fos.getChannel(), Encoding)

    try {
    val srcTxtLines = io.Source.fromFile(srcFile,"utf-8").getLines
    srcTxtLines.foreach(l => {
      val mergedTag = l.replace("_", "")
      val tokenTag = mergedTag.replaceAll("(\\s){2,}", "_")
      if(tokenTag != "#_DELM"){
    	  writer.write(tokenTag)
    	  if(tokenTag == "._DELM"){
    	    writer.write("\n")
    	  }else{
    	    writer.write(" ")
    	  }
      }
      
    })
    }  finally {
        writer.close()
    }
    
  }
  
}