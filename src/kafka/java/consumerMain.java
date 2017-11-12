package kafka.java;

public class consumerMain {
	public static void main(String[] args) {
		
		 
		
		Consumer kafka_Consumer = new Consumer();  
	        try {  
	        	
	            kafka_Consumer.execute();  
	            Thread.sleep(20000);  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        } finally {  
	            kafka_Consumer.shutdown();  
	        }  
	}
	
}

