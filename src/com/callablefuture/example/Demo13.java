package com.callablefuture.example;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo13 {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newCachedThreadPool();

		// If I want to return a type, use Callable().
		/*
		 * executor.submit(new Runnable() { public void run() { Random random =
		 * new Random(); int duration = random.nextInt(4000);
		 * System.out.println("Starting... "); try { Thread.sleep(duration); }
		 * catch (InterruptedException e) { e.printStackTrace(); }
		 * System.out.println("Finished."); } });
		 */
		Future<Integer> future = executor.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {

				Random random = new Random();
				int duration = random.nextInt(4000);
				if (duration > 2000) {
					throw new IOException("Sleeping for too long!");
				}
				System.out.println("Starting... ");
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Finished.");
				return duration;

			}

		});
		executor.shutdown();
		try {
			// future.get() will lock the thread to get the result.
			System.out.println("Duration is: " + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			// If e.printStackTrace(), then throws: java.util.concurrent.ExecutionException: java.io.IOException: Sleeping for too long!
			IOException ex = (IOException) e.getCause();
			System.out.println(ex.getMessage()); // This will just throw the message: Sleeping for too long!
		}

	}

}
