package project1;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main implements Runnable
{
	private ReentrantLock lock;
    private static int counter = 0;
    private static final int limit = 25;
    private static final int threadPoolSize = 5;
    
	
	public Main(ReentrantLock lock) 
    {
        this.lock = lock;
    }
	
  public static void main(String[] args) throws Exception
	{
		  
	  ReentrantLock sharedLock = new ReentrantLock();
      ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
     
      for (int i = 0; i < threadPoolSize; i++) {
          executorService.submit(new Main(sharedLock));
      }
      executorService.shutdown();
      
      
    }
  
   
  @Override
  public void run() 
  {
	  long start = System.nanoTime();
	  //try {                                                         //single lock acquire for whole process
         // lock.lock();
      while (counter < limit) 
      {
    	  try {                                                      //acquiring lock multiple times
          lock.lock();
    	      try {
    	          System.out.println(Thread.currentThread().getName() + " : " + counter);
    	    	  Thread.sleep(10);
    	          counter++;
    	      } finally {lock.unlock();}
    		  
    		  
    		  
    	  }catch(Exception e) {}
      }
	 // } finally {lock.unlock();}
	  long finish = System.nanoTime();
      long timeElapsed = finish - start;
	  System.out.println(timeElapsed+ "............................"); 
  }
  
 
  
  
 }



