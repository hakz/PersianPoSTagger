import java.io.InputStream
import java.io.FileInputStream
import opennlp.tools.postag.POSModel
import opennlp.tools.util.PlainTextByLineStream
import opennlp.tools.postag.WordTagSampleStream
import opennlp.tools.postag.POSTaggerME
import opennlp.tools.util.TrainingParameters
import java.io.IOException
import opennlp.tools.postag.POSTaggerFactory
import opennlp.tools.util.model.ModelType
import java.io.OutputStream
import java.io.BufferedOutputStream
import java.io.FileOutputStream

object Train {

  def main(args: Array[String]): Unit = {
    
    trainPoSTagger("corpus/BijanKhan_opennlp.txt", "model/fa-pos-maxent.bin")
    
  }
  
  
  def trainPoSTagger(trainingCorpus: String, targetModelFile:String): Unit = {
  
    var model: POSModel = null;
  	var dataIn: InputStream = null;
    var modelOut: OutputStream = null;
    
  	try {
  		dataIn = new FileInputStream(trainingCorpus);
  		val lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
  		val sampleStream = new WordTagSampleStream(lineStream);
  		val trainParams = new TrainingParameters();
        trainParams.put("model", ModelType.MAXENT.name());
  		model = POSTaggerME.train("fa", sampleStream, trainParams, null, null);
  	}
  	catch {
      case e: IOException => e.printStackTrace()
    }
  	finally {
  		if (dataIn != null) {
  			try {
  				dataIn.close();
  			}
  			catch {
  				case e: IOException => e.printStackTrace()
  			}
  		}
  	}
  	
  	//=========================================
  	
  	try {
  		modelOut = new BufferedOutputStream(new FileOutputStream(targetModelFile));
  		model.serialize(modelOut);
  	}
  	catch  {
  		// Failed to save model
  		case e: IOException => e.printStackTrace();
  	}
  	finally {
  		if (modelOut != null) {
  			try {
  				modelOut.close();
  			}
  			catch {
  				// Failed to correctly save model.
  				// Written model might be invalid.
  				case e: IOException => e.printStackTrace();
  			}
  		}
  	}
    
  } //end of train
  
}