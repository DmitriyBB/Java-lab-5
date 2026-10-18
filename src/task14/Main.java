package task14;

public class Main {

    record Key(String name) {}

    public static void main(String[] args) {
        LruCache<String, Integer> cache = new LruCache<>(3);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        System.out.println("filled:          " + cache.keysFromOldest());

        cache.put("d", 4);
        System.out.println("put d, evicts a: " + cache.keysFromOldest() + " get(a) = " + cache.get("a").orElse(null));

        cache.get("b");
        System.out.println("get b:           " + cache.keysFromOldest() + " (b is now the newest)");

        cache.put("e", 5);
        System.out.println("put e, evicts c: " + cache.keysFromOldest());

        cache.put("b", 20);
        System.out.println("put b again:     " + cache.keysFromOldest() + " size " + cache.size() + ", b = " + cache.get("b").orElse(null));

        System.out.println("miss does not change order: get(zzz) = " + cache.get("zzz").orElse(null) + " " + cache.keysFromOldest());

        LruCache<Key, String> byRecord = new LruCache<>(2);
        byRecord.put(new Key("x"), "first");
        byRecord.put(new Key("y"), "second");
        System.out.println("equal key, new instance: " + byRecord.get(new Key("x")).orElse(null));
        byRecord.put(new Key("z"), "third");
        System.out.println("after put z:     " + byRecord.keysFromOldest());

        try {
            cache.put(null, 1);
        } catch (NullPointerException e) {
            System.out.println("null key:        NullPointerException " + e.getMessage());
        }
        try {
            cache.put("q", null);
        } catch (NullPointerException e) {
            System.out.println("null value:      NullPointerException " + e.getMessage());
        }
        try {
            new LruCache<String, String>(0);
        } catch (IllegalArgumentException e) {
            System.out.println("capacity 0:      " + e.getMessage());
        }
    }
}
