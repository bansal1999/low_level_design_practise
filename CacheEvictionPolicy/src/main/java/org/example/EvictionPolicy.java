package org.example;

import java.util.HashMap;
import java.util.Map;

abstract class EvictionPolicy {
    protected int capacity;
    protected Map<Integer, CacheNode> cache;

    public EvictionPolicy(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
    }

    public abstract String get(int key);
    public abstract void put(int key, String value);
    public abstract void evict();
    public abstract boolean isFull();

    public int size(){
        return cache.size();
    }

    public boolean containsKey(int key){
        return cache.containsKey(key);
    }
}


