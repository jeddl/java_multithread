package com.sync.example;

public class Demo4 {
	// volatile does not work here
	private int count = 0;
	
	public synchronized void increment() {
		count++;
	}
	
	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for(int i = 0; i < 10000; i++) {
					increment();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}
			}
		});
		
		t1.start();
		t2.start();
	
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Count = " + count);
	}
	
	public static void main(String[] args) {
		Demo4 demo = new Demo4();
		demo.doWork();
	}
}
