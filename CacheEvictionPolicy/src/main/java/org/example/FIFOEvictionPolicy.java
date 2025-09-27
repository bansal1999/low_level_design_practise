package org.example;

import java.util.LinkedList;
import java.util.Queue;

class FIFOEvictionPolicy extends EvictionPolicy {
    private Queue<Integer> insertionOrder;

    public FIFOEvictionPolicy(int capacity) {
        super(capacity);
        this.insertionOrder = new LinkedList<>();
    }

    @Override
    public String get(int key) {
        CacheNode node  =  cache.get(key);
        return node != null ? node.value : null;
    }

    @Override
    public void put(int key, String value) {

    }

    @Override
    public void evict() {

    }

    @Override
    public boolean isFull() {
        return false;
    }
}
