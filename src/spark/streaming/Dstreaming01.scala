package spark.streaming
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.StreamingContext._;
import org.apache.spark.streaming._;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;

object Dstreaming01 {
  
  def main(args:Array[String]):Unit={
    val conf=new SparkConf().setAppName("dstreaming").setMaster("local[3]");
     conf.set("spark.testing.memory", "471859200");
    
    val ssc=new StreamingContext(conf,Seconds(1));
    ssc.sparkContext.setLogLevel("WARN")
     
    val lines = ssc.socketTextStream("192.168.1.17", 9999)
    val words = lines.flatMap(_.split(" "))
    val pairs = words.map( (_,1))
    val wordCounts = pairs.reduceByKey(_ + _)
    
    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()
  }
}