import java.io.InputStream
import java.io.FileInputStream
import opennlp.tools.postag.POSModel
import java.io.IOException
import opennlp.tools.postag.POSTaggerME
import java.io.StringWriter
import org.apache.commons.io.IOUtils

object Sample {

  def main(args: Array[String]): Unit = {
 
  var modelIn: InputStream = null;
  try {
	modelIn = new FileInputStream("model/fa-pos-maxent.bin");
	val model = new POSModel(modelIn);
	val tagger : POSTaggerME = new POSTaggerME(model);
	
	//NOTE: I could simply do this, 
	//val txt = io.Source.fromFile("test/news.txt").getLines
	//but somehow, scala has problem reading Perso-Arabic text See
	// http://scala-programming-language.1934581.n4.nabble.com/Scala-handling-of-Perso-Arabic-script-different-from-Java-td1952301.html

	val encoding = "UTF-8"
	val foi = new FileInputStream("test/news.txt")
    val writer = new StringWriter();
	IOUtils.copy(foi, writer, encoding);
	val txt = writer.toString();

	val lines = txt.split("\n")
	
	lines.foreach(line =>{
	  val sent = line.split(" ") // need a proper persian tokenizer
	  val tags = tagger.tag(sent);
	  val tuples = sent.zip(tags)
	  tuples.foreach(t => println(t))
	})
	
	
  }
  catch  {
	  // Model loading failed, handle the error
  	case e: IOException => e.printStackTrace();
  }
  finally {
	  if (modelIn != null) {
		  try {
			  modelIn.close();
		  }
		  catch  {
		    case e: IOException => e.printStackTrace()
		  }
	  }
  }
  
  
  
    
  }
  
}