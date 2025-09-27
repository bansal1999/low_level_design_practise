package org.example;

class EvictionPolicyFactory {

    public static EvictionPolicy createPolicy(EvictionPolicyType type, int capacity) {
        return switch (type) {
            case LRU -> new LRUEvictionPolicy(capacity);
            case LFU -> new LFUEvictionPolicy(capacity);
            case FIFO -> new FIFOEvictionPolicy(capacity);
            default -> throw new IllegalArgumentException("Unknown Eviction Policy: " + type);
        };
    }
}
