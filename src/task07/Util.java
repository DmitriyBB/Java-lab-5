package task07;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;

public final class Util {

    private Util() {}

    public static <T> T firstOrThrow(List<T> items) {
        if (items.isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        return items.get(0);
    }

    public static <T> void swap(List<T> items, int i, int j) {
        T first = items.get(i);
        items.set(i, items.get(j));
        items.set(j, first);
    }

    public static <T, K> Map<K, T> indexBy(List<T> items, Function<T, K> keyOf) {
        Map<K, T> index = new LinkedHashMap<>();
        for (T item : items) {
            K key = keyOf.apply(item);
            T previous = index.putIfAbsent(key, item);
            if (previous != null) {
                throw new IllegalStateException("Duplicate key " + key + ": " + previous + " and " + item);
            }
        }
        return index;
    }
}
