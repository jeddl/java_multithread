package com.vola.example;

import java.util.Scanner;

class Processor extends Thread {
	
	private volatile boolean running = true;
	public void run() {
		
		while(running) {
			System.out.println("Hello");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void shutdown() {
		
		running = false;
		
	}
}

public class Demo3 {
	
	public static void main(String[] args) {
		
		Processor proc1 = new Processor();
		proc1.start();
		System.out.println("Press Enter to stop... ");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		proc1.shutdown();
		scanner.close();
		
	}
}
