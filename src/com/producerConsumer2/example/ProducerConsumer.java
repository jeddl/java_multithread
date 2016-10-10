package com.producerConsumer2.example;

public class ProducerConsumer {
	public static void main(String[] args) {
        MT thread = new MT();
        new Producer(thread);
        new Consumer(thread);
    }
}

class MT {
    int n;
    boolean valueSet = false;
    synchronized int get() {
        if (!valueSet)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught!");
            }
        System.out.println("Got " + n);
        valueSet = false;
        notify();
        return n;
    }
    synchronized void put(int n) {
        if (valueSet)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught!");
            }
        this.n = n;
        System.out.println("Put " + n);
        valueSet = true;
        notify();
    }
}

class Producer implements Runnable {
    MT thread;
    Producer(MT thread) {
        this.thread = thread;
        new Thread(this, "Producer").start();
    }
    public void run() {
        int i = 0;
        while (i < 10) {
            thread.put(++i);
        }
    }
}

class Consumer implements Runnable {
    MT thread;
    Consumer(MT thread) {
        this.thread = thread;
        new Thread(this, "Consumer").start();
    }
    public void run() {
        int i = 0;
        while (i < 10) {
            thread.get();
            ++i;
        }
    }
}
