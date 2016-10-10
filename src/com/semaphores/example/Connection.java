package com.semaphores.example;

import java.util.concurrent.Semaphore;

public class Connection {
	
	// Singleton
	private static Connection instance = new Connection();
	private Semaphore semaphore = new Semaphore(10, true);
	private int connections = 0;
	
	private Connection() {
		
	}
	
	public static Connection getInstance() {
		
		return instance;
		
	}

	public void connect() {
		
		try {
			semaphore.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			doConnection();
		} finally {
			// Moved here
			semaphore.release();
		}
		
	}
	
	// The reason why separate these 2 methods is that if try catch for thread.sleep() is interrupted, semaphore.release() will never be reached.
	public void doConnection() {
		
		synchronized (this) {
			connections++;
			System.out.println("Current connections: " + connections);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (this) {
			connections--;
			System.out.println("New current connections: " + connections);
		}
//		semaphore.release();
		
	}
	
}
