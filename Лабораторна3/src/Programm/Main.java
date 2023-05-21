package Programm;



class Main {
    public static void main(String[] args) {
        int capacity = 3; // Максимальна місткість сховища
        int totalItems = 5; // Загальна кількість "продукції" для обробки

        Buffer buffer = new Buffer(capacity);

        Producer producer = new Producer(buffer, totalItems);
        Consumer consumer = new Consumer(buffer, totalItems);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}