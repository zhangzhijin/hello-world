package spark.streaming

import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming._;
import java.io.File;
import org.apache.spark.SparkConf;




object sparkStreamCheckPoint {
  
  
  
  def main(args: Array[String])={
  
  
       val checkpointDirectory:String="";
      val ip:String="";
      val outputPath:String="";
      val port:Int=0;
      
 val ssc=StreamingContext.getOrCreate(checkpointDirectory,()=>createContext(ip,port,outputPath,checkpointDirectory))
  
    ssc.start()
    ssc.awaitTermination()
}
  
   def createContext(ip: String, port: Int, outputPath: String, checkpointDirectory: String):StreamingContext={

    println("Creating new context");
  
    val outputFile = new File(outputPath)
    if (outputFile.exists()) 
      outputFile.delete();
    val sparkConf = new SparkConf().setAppName("RecoverableNetworkWordCount")
  
   
    // Create the context with a 1 second batch size
    val ssc = new StreamingContext(sparkConf, Seconds(1))
    ssc.checkpoint(checkpointDirectory)
  
 
    val lines = ssc.socketTextStream(ip, port)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map((_, 1)).reduceByKey(_ + _);
    
      return ssc
    
  }
  }
 