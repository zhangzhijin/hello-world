package kafka
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.Properties
import java.util.Random
import java.util.Date

object producerKafka {
  
   private val random = new Random()
  
   //消费的金额（0-9）
  def payMount() : Double = {
    random.nextInt(10)
  }
     //随机获得用户名称
  def getUserName(users:Array[User]) : User = {
    users(random.nextInt(users.length))
  }
  
  def main(args:Array[String]):Unit={
    var brokers="192.168.1.14:9092"
  
    
     val props = new Properties();
 props.put("bootstrap.servers", "192.168.1.14:9092");
 props.put("acks", "all");

 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

 val  producer = new KafkaProducer[String, String](props);
 while(true)
 {
   //var producerRecode= new ProducerRecord[String, String]("testtopic","zhang")
     producer.send(new ProducerRecord[String, String]("test","zhang","zna"))
 }

 //producer.close();
    
 
    
  }
}