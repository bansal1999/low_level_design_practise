package org.example;

import java.util.HashMap;
import java.util.Map;

class CacheSystem {
    private EvictionPolicy currentPolicy;
    private EvictionPolicyType currentPolicyType;
    private int capacity;

    public CacheSystem(EvictionPolicyType initialPolicyType, int capacity) {
        this.currentPolicyType = initialPolicyType;
        this.capacity = capacity;
        this.currentPolicy = EvictionPolicyFactory.createPolicy(initialPolicyType, capacity);
    }

    public String get(int key) {
        String value = currentPolicy.get(key);
        return value != null ? value : "-1";
    }

    public void put(int key, String value) {
        currentPolicy.put(key, value);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public EvictionPolicyType getCurrentPolicyType() {
        return currentPolicyType;
    }

    public int size() {
        return currentPolicy.size();
    }

    public void switchEvictionPolicy(EvictionPolicyType newPolicyType) {
        if (this.currentPolicyType == newPolicyType) {
            return;
        }

        //Save the state for the cache state
        Map<Integer, String> currentData = new HashMap<>();
        for (Map.Entry<Integer, CacheNode> entry : currentPolicy.cache.entrySet()) {
            currentData.put(entry.getKey(), entry.getValue().value);
        }

        //create new policy and migrate data
        this.currentPolicy = EvictionPolicyFactory.createPolicy(newPolicyType, capacity);
        this.currentPolicyType = newPolicyType;

        //Migrate existing data to new policy
        for (Map.Entry<Integer, String> entry : currentData.entrySet()) {
            currentPolicy.put(entry.getKey(), entry.getValue());
        }
    }


}
