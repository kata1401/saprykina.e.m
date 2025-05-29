package main.service;

import java.util.*;

public class CacheService {
    private final Map<String, Object> cache = new HashMap<>();
    private final Map<String, Boolean> readOnlyFlags = new HashMap<>();

    public void put(String key, Object value, boolean readOnly) {
        cache.put(key, value);
        readOnlyFlags.put(key, readOnly);
    }

    public Object get(String key) {
        return cache.get(key);
    }

    public void refreshCache(String key) {
        if (!readOnlyFlags.getOrDefault(key, false)) {
            cache.remove(key);
        }
    }
}