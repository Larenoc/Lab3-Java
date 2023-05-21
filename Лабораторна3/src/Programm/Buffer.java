package Programm;

import java.util.LinkedList;

class Buffer {
    private LinkedList<Integer> queue;
    private int capacity;

    public Buffer(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }

        queue.add(item);
        System.out.println("Виробник додав: " + item);
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }

        int item = queue.removeFirst();
        System.out.println("Споживач використав: " + item);
        notifyAll();
    }
}

class Producer implements Runnable {
    private Buffer buffer;
    private int totalItems;

    public Producer(Buffer buffer, int totalItems) {
        this.buffer = buffer;
        this.totalItems = totalItems;
    }

    public void run() {
        try {
            for (int i = 1; i <= totalItems; i++) {
                buffer.produce(i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private Buffer buffer;
    private int totalItems;

    public Consumer(Buffer buffer, int totalItems) {
        this.buffer = buffer;
        this.totalItems = totalItems;
    }

    public void run() {
        try {
            for (int i = 1; i <= totalItems; i++) {
                buffer.consume();
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


