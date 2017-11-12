package spark.streaming
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.StreamingContext._;
import org.apache.spark.streaming._;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql._;

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.ConsumerStrategies._;
import org.apache.spark.streaming.kafka010.LocationStrategies._;

object kakfaDirectStream {
  
  def main(args:Array[String]):Unit={
    val conf=new SparkConf().setAppName("kakfaDirectStream").setMaster("local[2]");
    //val conf=new SparkConf().setAppName("kakfaDirectStream").setMaster("spark://192.168.1.17:7077");
     conf.set("spark.testing.memory", "471859200");
     
     val scc=new StreamingContext(conf,Seconds(5));
     scc.sparkContext.setLogLevel("WARN");
     
    val kafkaParams = Map[String, Object](
  "bootstrap.servers" -> "hadoop4:9092,hadoop5:9092,hadoop6:9092",
  "key.deserializer" -> classOf[StringDeserializer],
  "value.deserializer" -> classOf[StringDeserializer],
  "group.id" -> "test",
  "auto.offset.reset" -> "latest",
  "enable.auto.commit" -> (false: java.lang.Boolean)
)
    
val topics = Array("testTopic")
val stream = KafkaUtils.createDirectStream[String, String](
  scc,
  PreferConsistent,
  Subscribe[String, String](topics, kafkaParams)
)
    
val streamPair=stream.map(record => (record.key, record.value));
    
 val streamValue=stream.map(record => record.value);
  //val streamWords=  streamPair.map(recorde=>(recorde._2,1));
    
  //val wordCount=  streamWords.reduceByKey(_+_);
  //wordCount.print()
  
  //val filter=streamPair.filter(a=>a._2=="zhang");
  
  //val count=streamPair.count();
    
    streamValue.foreachRDD {
      rdd=>
          val spark = SparkSession.builder.config(rdd.sparkContext.getConf).getOrCreate()
          import spark.implicits._
       val wordsDataFrame = rdd.toDF("word")
        wordsDataFrame.createOrReplaceTempView("words")
         val wordCountsDataFrame = 
    spark.sql("select word, count(*) as total from words group by word")
    wordCountsDataFrame.show()
       
    }
   
   
    scc.start();
    
    scc.awaitTermination()
    
    
     
  }
}