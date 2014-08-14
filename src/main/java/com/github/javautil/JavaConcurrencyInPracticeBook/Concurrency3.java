package com.github.javautil.JavaConcurrencyInPracticeBook;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;





public class Concurrency3 {
	
	public static void main(String[] args) {
		final MyQueue<Integer> myQueue = new MyQueue<Integer>();
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		threadPool.execute(new Runnable() {
			
			public void run() {
				for (int j = 0; j < 100; j++) {
					myQueue.put(j);
				}
				
			}
		});

		threadPool.execute(new Runnable() {
			
			public void run() {
				for (int j = 0; j < 100; j++) {
					myQueue.get();
				}
				
			}
		});
		
		threadPool.shutdown();
		
		
		
	}

}

class MyQueue<T>{
	private LinkedBlockingQueue<T> queue = new LinkedBlockingQueue<T>();
	private ReentrantReadWriteLock lock =  new ReentrantReadWriteLock();
	private Lock readLock = lock.readLock();
	private Lock writeLock = lock.writeLock();
	
	public void put(T value) {
		writeLock.lock();
		try {
			queue.put(value);
			System.out.println("put " + value);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			writeLock.unlock();
		}
	}
	
	
	public T get() {
		readLock.lock();
		try {
			T t = queue.take();
			System.out.println("get " + t);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			readLock.unlock();
		}
		return null;
	}
}

