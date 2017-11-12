package kafka
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords

import java.util.Properties
import java.util.Random
import java.util.Date

object consumerKafka {
  
   
  
  def main(args:Array[String]):Unit={
    var brokers="192.168.1.14:9092"
    var topics =new java.util.ArrayList[String]();
    topics.add(0,"test");
    
   
       val props = new Properties();
     props.put("bootstrap.servers",brokers);
     props.put("group.id", "test");
     props.put("enable.auto.commit", "true");
     props.put("auto.commit.interval.ms", "100");
     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
     
    val consumer:KafkaConsumer[String, String]  = new KafkaConsumer[String, String](props);
    
     consumer.subscribe(topics);
     while (true) {
         val records:ConsumerRecords[String, String] = consumer.poll(5);
         
         val  it=records.iterator()
         
         while (it.hasNext()){
           println(it.next().key())
           
         }
         
     }
    
  }
}