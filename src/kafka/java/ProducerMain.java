package kafka.java;

public class ProducerMain {
	public static void main(String[] args) {
		
		 
		 HandlerProducer handlerProducer = new HandlerProducer();  
		
		  Thread t1 = new Thread(handlerProducer); 
		 t1.start();  
		 
	}
	
}

