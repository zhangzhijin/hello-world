package kafka.java;
import java.util.*;
import org.apache.kafka.clients.consumer.*;

public class ConsumerAutoCommit {

	private  Properties props = new Properties();
	    private KafkaConsumer<String, String> consumer;
	    
	      void receiveMessage(){
	    	 
	    	 props.put("bootstrap.servers","hadoop4:9092,hadoop5:9092,hadoop6:9092");
		     props.put("group.id", "test");
		     props.put("enable.auto.commit", "true");
		     props.put("auto.commit.interval.ms", "1000");
		     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		     
		     consumer= new KafkaConsumer<>(props);
		     
	     consumer.subscribe(Arrays.asList("testTopic"));
	     while (true) {
	         ConsumerRecords<String, String> records = consumer.poll(100);
	         for (ConsumerRecord<String, String> record : records)
	             System.out.printf("接收消息："+"offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
	     }
	     }
}
