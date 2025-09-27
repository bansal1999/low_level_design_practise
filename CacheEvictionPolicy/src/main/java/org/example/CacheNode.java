package org.example;

public class CacheNode {
    int key;
    String value;
    CacheNode prev, next;
    int frequency;
    long accessTime;
    long insertionOrder;

    public CacheNode(int key, String value) {
        this.key = key;
        this.value = value;
        this.frequency = 1;
        this.accessTime = System.nanoTime();
        this.insertionOrder = System.nanoTime();

    }
}
