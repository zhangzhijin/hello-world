package kafka.java;

public class ProducerConsumerMain {

	public static void main(String[] args) {

Thread producerThread=new Thread(new HandlerProducer());
Thread consumerThread=new Thread(new HandoerConsumerAutoCommit());

producerThread.start();
 consumerThread.start();




	}
	
	void producerConsumer()
	{
		Thread producerThread=new Thread(new HandlerProducer());
		Thread consumerThread=new Thread(new HandoerConsumerAutoCommit());

		producerThread.start();
		 consumerThread.start();
		
	}

}
