package kafka.java;

public class HandoerConsumerAutoCommit implements Runnable{
	ConsumerAutoCommit consumerAutoCommit=new ConsumerAutoCommit();
	

	@Override
	public void run() {
		consumerAutoCommit.receiveMessage();
		
	}

}
