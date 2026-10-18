package task14;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class LruCache<K, V> {

    private final int capacity;
    private final Map<K, V> entries;

    public LruCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must be positive, got " + capacity);
        }
        this.capacity = capacity;
        this.entries = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > LruCache.this.capacity;
            }
        };
    }

    public Optional<V> get(K key) {
        Objects.requireNonNull(key, "key");
        return Optional.ofNullable(entries.get(key));
    }

    public void put(K key, V value) {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(value, "value");
        entries.put(key, value);
    }

    public int size() {
        return entries.size();
    }

    public List<K> keysFromOldest() {
        return new ArrayList<>(entries.keySet());
    }
}
