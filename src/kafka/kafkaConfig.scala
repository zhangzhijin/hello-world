package kafka
import java.util.Properties
import org.apache.kafka.clients.producer.ProducerConfig;


class KafkaConfig  {
  private var topic:String="";
  private var brokers:String="";
  
  var props=new Properties()
  
  
  //¹¹ÔìÆ÷
    def this(topic:String,brokers:String)={
    this();
     this.topic=topic;
      this.brokers=brokers;
     }
    
    def getKafkaProducerProperties:Properties={
      
      props.put("bootstrap.servers",brokers);
      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
      props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")
      
      return props;
    
    }
    
  
}