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
        CacheNode node = cache.get(key);
        return node != null ? node.value : null;
    }

    @Override
    public void put(int key, String value) {
        if (cache.containsKey(key)) {
            cache.get(key).value = value;
        } else {
            if (isFull()) {
                evict();
            }
        }

        CacheNode newNode = new CacheNode(key, value);
        cache.put(key, newNode);
        insertionOrder.offer(key);
    }

    @Override
    public void evict() {
        if (!insertionOrder.isEmpty()) {
            int oldestKey = insertionOrder.poll();
            cache.remove(oldestKey);
        }

    }

    @Override
    public boolean isFull() {
        return cache.size() >= capacity;
    }
}
