package com.github.javautil.JavaConcurrencyInPracticeBook;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Concurrency1 {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		final Sequence sequence = new Sequence();
		final Set<Integer> set = new HashSet<Integer>();
		int n = 50;
		for (int i = 0; i <= n; i++) {
			threadPool.submit(new Runnable() {
				
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.err.println(sequence.getNext());
					
					try {
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
		}
		
		threadPool.shutdown();
	}
}

//非线程安全
class Sequence{
	private int value = 0;
	public int getNext(){
		return value++;
	}
}