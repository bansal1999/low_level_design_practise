package org.example;

public class LFUEvictionPolicy extends EvictionPolicy{
    public LFUEvictionPolicy(int capacity) {
        super(capacity);
    }

    @Override
    public String get(int key) {
        return "";
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
