package com.github.javautil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanPractice {
	static boolean b = true;
	static AtomicBoolean atomicBoolean = new AtomicBoolean(true);

	public static void main(String[] args) {
		noSafe();
		System.err.println("///////////////////////////////");
		safe();
	}
	
	public static void noSafe() {
		
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				
				public void run() {
					if (b) {
						try {
							TimeUnit.SECONDS.sleep(3);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						System.out.println(Thread.currentThread().getName() + "  enter ");
						
						try {
							TimeUnit.SECONDS.sleep(3);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						b = false;
						System.out.println(Thread.currentThread().getName() + "  leave ");
						
						try {
							TimeUnit.SECONDS.sleep(3);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else {
						System.out.println(Thread.currentThread().getName() + " ///// enter fail ");
					}
					
				}
			}).start();
		}
	}
	
	
	public static void safe() {
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				
				public void run() {
					if (atomicBoolean.compareAndSet(true, false)) {
						try {
							TimeUnit.SECONDS.sleep(3);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						System.out.println(Thread.currentThread().getName() + " atomicBoolean   enter  ");
						
						
						atomicBoolean.set(true);
						
						System.out.println(Thread.currentThread().getName() + " atomicBoolean   leave  ");
					
					
					}else {
						System.out.println(Thread.currentThread().getName() + " /////atomicBoolean  enter fail ");
					}
				}
			}).start();
		}
	}

}
