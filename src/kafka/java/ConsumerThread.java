package kafka.java;

import java.util.Collections;  
import java.util.List;  
  
import org.apache.kafka.clients.consumer.ConsumerRecord;  
import org.apache.kafka.clients.consumer.ConsumerRecords;  
import org.apache.kafka.clients.consumer.KafkaConsumer;  
import org.apache.kafka.clients.consumer.OffsetAndMetadata;  
import org.apache.kafka.common.TopicPartition;  
  
/** 
 * ��������,���work�߳�,�ѱ�֤������Ϣ���ѵ�˳���� 
 *  
 * @author tanjie 
 * 
 */  
public final class ConsumerThread implements Runnable {  
  
    private ConsumerRecords<String, String> records;  
  
    private KafkaConsumer<String, String> consumer;  
  
    public ConsumerThread(ConsumerRecords<String, String> records,  
            KafkaConsumer<String, String> consumer) {  
        this.records = records;  
        this.consumer = consumer;  
    }  
  
    @Override  
    public void run() {  
        for (TopicPartition partition : records.partitions()) {  
            List<ConsumerRecord<String, String>> partitionRecords = records.records(partition); 
            
            for (ConsumerRecord<String, String> record : partitionRecords) {  
                System.out.println("��ǰ�߳�:" + Thread.currentThread() + ","  
                        + "ƫ����:" + record.offset() + "," + "����:"  
                        + record.topic() + "," + "����:" + record.partition()  
                        + "," + "��ȡ����Ϣ:" + record.value());  
            }  
            // �������Լ��ֶ��ύ���ѵ�offest,ȷ����Ϣ��ȷ��������ύ  
            long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();  
            consumer.commitSync(Collections.singletonMap(partition,  
                    new OffsetAndMetadata(lastOffset + 1))
            		);  
            
        }  
    }  
}  
