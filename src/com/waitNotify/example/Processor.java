package com.waitNotify.example;

import java.util.Scanner;

public class Processor {

	public void producer() throws InterruptedException {
		
		synchronized (this) {
			System.out.println("Producer thread running... ");
			wait();
			System.out.println("Resumed.");
		}
		
	}
	public void consumer() throws InterruptedException {
		
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		synchronized (this) {
			System.out.println("Waiting for return key... ");
			scanner.nextLine();
			System.out.println("Return key pressed.");
			scanner.close();
			notify();
			// After notify(), it is going to wait until this synchronized block is released.(meaning after 5s)
			Thread.sleep(5000);
		}
		
	}
	
}
