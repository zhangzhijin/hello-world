package kafka.java;

 
import java.util.Properties;  
 
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;

import org.apache.kafka.common.*;
import org.apache.kafka.common.KafkaException;
 
 
  
 class  Producer {  
	
	private KafkaProducer<String,String> producer;
	
	private Properties props = new Properties();
	 
	void sentMessage(int i){
		
		props.put("bootstrap.servers", "hadoop4:9092,hadoop5:9092,hadoop6:9092");
		props.put("acks", "all");
		 props.put("retries", 0);
		 props.put("batch.size", 10);
		 props.put("linger.ms", 1);
		 props.put("buffer.memory", 33554432/4);//33554432
		 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 
		 producer=new KafkaProducer<String,String>(props);
		 
		
		 try
		 {    
			// while(true){	
			 
		 //for (  i = 0; i < 100; i++)
		 {  
			 String messageKey="key"+i;
			 String messageValue="V"+i;
			 producer.send(new ProducerRecord<String, String>("testTopic", messageKey, messageValue));
		      System.out.println(  "·¢ËÍÏûÏ¢:k="+messageValue+";v="+messageValue);
		     
		   
		 }
		 
		  }
	 catch (Exception  e) {
	     // We can't recover from these exceptions, so our only option is to close the producer and exit.
		 producer.close();
	 }
		 producer.close();

	}
	}
