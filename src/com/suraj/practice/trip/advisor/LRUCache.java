package com.suraj.practice.trip.advisor;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

  Map<Integer, Integer> cache;
  int size;

  public LRUCache(int capacity) {
    cache = new LinkedHashMap<>();
    size = capacity;
  }

  public int get(int key) {
    Integer value = cache.get(key);
    if (value != null) {
      cache.remove(key);
      cache.put(key, value);
    } else {
      value = -1;
    }
    return value;
  }

  public void put(int key, int value) {
    if (cache.get(key) != null) {
      cache.remove(key);
    } else {
      if (cache.keySet().size() == size) {
        int lastKey = 0;
        for (Map.Entry<Integer, Integer> entry : cache.entrySet()) {
          lastKey = entry.getKey();
          break;
        }
        cache.remove(lastKey);
      }
    }
    cache.put(key, value);
  }

  public static void main(String[] args) {
    LRUCache cache = new LRUCache(2);
    cache.put(2, 1);
    cache.put(1, 1);
    cache.put(2, 3);
    cache.put(4, 1);
    cache.get(1);
    cache.get(2);
  }
}
