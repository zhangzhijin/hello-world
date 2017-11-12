package spark.streaming
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.StreamingContext._;
import org.apache.spark.streaming._;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql._;

object StructuredStreamingKafka {
  
  def main(args:Array[String]):Unit={
    //val conf=new SparkConf().setAppName("dstreaming").setMaster("spark://192.168.1.105:7077");
    val conf=new SparkConf().setAppName("dstreaming").setMaster("local[3]");
     conf.set("spark.testing.memory", "471859200");
     
     val sparkSession=SparkSession.builder().config(conf).getOrCreate();
     
    val inputStream= sparkSession.readStream.format("kafka")
     .option("kafka.bootstrap.servers", "192.168.1.14:9092")
      .option("subscribe", "topictest")
      .load()
    
     import sparkSession.implicits._;
    
    var query=inputStream.select($"name", $"age")
    .writeStream.outputMode("append").format("console").start();
    
    query.awaitTermination()
    
    
     
  }
}