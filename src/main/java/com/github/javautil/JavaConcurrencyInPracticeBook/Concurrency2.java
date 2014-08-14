package com.github.javautil.JavaConcurrencyInPracticeBook;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class Concurrency2 {

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 40; i++) {
			threadPool.submit(new Runnable() {
				
				public void run() {
					
					System.err.print(SingleInstance1.getInstance().getNum() + " ");
					
					
					try {
						TimeUnit.SECONDS.sleep(4);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			});
		}
		
		threadPool.shutdown();

	}
	
	

}

//单例延迟实例化，非线程安全
final class SingleInstance1{
	private static SingleInstance1 instance = null;
	private static AtomicInteger atomicInteger = new AtomicInteger(0);
	private SingleInstance1(){
		atomicInteger.incrementAndGet();
	}
	
	public static  SingleInstance1 getInstance() {
		if (null == instance) {
			System.out.println("null "); //应该只能输出一个null
			instance = new SingleInstance1();
			return instance;
		}
		return instance;
	}
	public static int getNum() {
		return atomicInteger.get();
	}
}
