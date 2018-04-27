package yyd.yun.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SequenceUtil {
	private static final long ONE_STEP = 10;
	private static final Lock LOCK = new ReentrantLock();
	private static long lastTime = System.currentTimeMillis();
	private static short lastCount = 0;
	private static int count = 0;
	
	@SuppressWarnings("finally")
	public static String nextId() {
		LOCK.lock();
		try {
			if (lastCount == ONE_STEP) {
				boolean done = false;
				while (!done) {
					long now = System.currentTimeMillis();
					if (now == lastTime) {
						try {
							Thread.currentThread();
							Thread.sleep(1);
						} catch (InterruptedException e) {
						}
						continue;
					} else {
						lastTime = now;
						lastCount = 0;
						done = true;
					}
				}
			}
			count = lastCount++;
		} finally {
			LOCK.unlock();
			return lastTime + "" + String.format("%03d", count);
		}
	}
	
	public static void main(String[]args){
		Runnable r1=new Runnable() {
			
			public void run() {
				System.out.println(nextId());
			}
		};
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		for(int i=0;i<10000;i++){
			executorService.execute(r1);
		}  
	}
}
