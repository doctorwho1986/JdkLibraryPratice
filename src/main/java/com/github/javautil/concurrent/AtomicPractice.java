package com.github.javautil.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicPractice {
	static int n = 0;
	static AtomicInteger atomicInteger = new AtomicInteger(0);
	static List<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) throws InterruptedException {
		notsafe();

		safe();
	}
	
	public static void notsafe() {
		ExecutorService threadPool = Executors.newFixedThreadPool(50);
		list.clear();
		for (int i = 0; i < 100; i++) {
			threadPool.submit(new Runnable() {
				
				public void run() {					
					list.add(n++);
									}
			});
			
		}
		
		threadPool.shutdown();
		System.out.println("not safe ");
		System.out.println(list);
		
		
	}
	
	public static void safe() {
		
		ExecutorService threadPool = Executors.newFixedThreadPool(50);
		list.clear();
		for (int i = 0; i < 100; i++) {
			threadPool.submit(new Runnable() {
				
				public void run() {					
					list.add(atomicInteger.incrementAndGet());
									}
			});
			
		}
		
		threadPool.shutdown();
		System.out.println("safe ");
		System.out.println(list);
	}

}
