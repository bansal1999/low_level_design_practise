package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Cache Eviction system!");

        System.out.println("=== Testing LRU Policy ===");
        CacheSystem cache = new CacheSystem(EvictionPolicyType.LRU, 2);

        cache.put(1, "A");
        cache.put(2, "B");
        System.out.println("get(1): " + cache.get(1)); // A (makes 1 most recent)
        cache.put(3, "C"); // Evicts 2 (least recently used)
        System.out.println("get(2): " + cache.get(2)); // -1 (not found)
        System.out.println("get(1): " + cache.get(1)); // A
        System.out.println("get(3): " + cache.get(3)); // C

//        System.out.println("\n=== Testing LFU Policy ===");
//        cache.switchEvictionPolicy(EvictionPolicyType.LFU);
    }
}