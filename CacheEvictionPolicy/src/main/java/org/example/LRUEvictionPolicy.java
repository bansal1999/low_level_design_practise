package org.example;

class LRUEvictionPolicy extends EvictionPolicy {

    private CacheNode head, tail;

    public LRUEvictionPolicy(int capacity) {
        super(capacity);

        // create dummy nodes head and tail node
        head = new CacheNode(0, "");
        tail = new CacheNode(0, "");
        head.next = tail;
        tail.next = head;
    }

    @Override
    public String get(int key) {
        CacheNode node = cache.get(key);
        if (node == null) {
            return null;
        }

        //most recently used --> move to head
        moveToHead(node);
        return node.value;
    }

    @Override
    public void put(int key, String value) {
        CacheNode existingNode = cache.get(key);

        if (existingNode != null) {
            existingNode.value = value;
            moveToHead(existingNode);
        } else {
            CacheNode newNode = new CacheNode(key, value);
            if (isFull()) {
                evict();
            }
            cache.put(key, newNode);
            addToHead(newNode);
        }

    }

    @Override
    public void evict() {
        if (tail.prev != head) {
            CacheNode lru = tail.prev;
            removeNode(lru);
            cache.remove(lru.key);
        }

    }

    @Override
    public boolean isFull() {
        return cache.size() >= capacity;
    }

    private void addToHead(CacheNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(CacheNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

    }

    private void moveToHead(CacheNode node) {
        removeNode(node);
        addToHead(node);

    }

}
