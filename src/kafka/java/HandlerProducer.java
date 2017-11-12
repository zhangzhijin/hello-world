package kafka.java;

import org.apache.kafka.clients.producer.KafkaProducer;

public class HandlerProducer implements Runnable {  
	  
	Producer producer =new Producer();  
	 
	
  
    @Override  
    public void run() { 
    	
    	try
    	{
    		int i=0;
    		
    		   while(true)
    	        {
    	        	if(i%10==0){
    	        		
    	        		Thread.sleep(10);
    	        	}
    	          //System.out.println("当前线程:" + Thread.currentThread().getName());
    	        	
    	         
    	          producer.sentMessage(i);
    	          i++;
    	    }
    		
    	}
    	catch(Exception e){
    		
    		
    	}
    	
    	
    	
     
                 
     
    }
}  