package com.countdownLatches.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {

	private CountDownLatch latch;

	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}

	public void run() {

		System.out.println("Thread started... ");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		latch.countDown();
		System.out.println("Latch countdown finished... ");

	}

}

public class Demo7 {
	public static void main(String[] args) {

		/* This given number must be equaled to(or less than) the latch.countDown times.
		 * Only if the number matches(or less than), latch.await() will end.
		 */
		CountDownLatch latch = new CountDownLatch(3); // e.g. 3 in this case
		// If the threads' number is less than the loop number, then after one thread finishes its job, it will start again until the loop ends
		ExecutorService executor = Executors.newFixedThreadPool(3); // e.g. 2, 1 in this case
		for (int i = 0; i < 3; i++) {
			executor.submit(new Processor(latch));
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Completed.");

	}
}
