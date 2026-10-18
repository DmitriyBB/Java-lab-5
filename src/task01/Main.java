package task01;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Main {

    static long sink;

    static long bestMicros(Runnable action) {
        long best = Long.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            long start = System.nanoTime();
            action.run();
            best = Math.min(best, System.nanoTime() - start);
        }
        return best / 1_000;
    }

    static Set<String> duplicates(List<String> items) {
        Set<String> seen = new HashSet<>();
        Set<String> result = new LinkedHashSet<>();
        for (String item : items) {
            if (!seen.add(item)) {
                result.add(item);
            }
        }
        return result;
    }

    static void compare(int size) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
        Random random = new Random(1);
        int[] positions = new int[2000];
        for (int i = 0; i < positions.length; i++) {
            positions[i] = random.nextInt(size);
        }

        System.out.println("size = " + size + ", best of 5 runs, microseconds");
        System.out.println("  get(i) x 2000   ArrayList: " + bestMicros(() -> {
            for (int p : positions) {
                sink += arrayList.get(p);
            }
        }) + "   LinkedList: " + bestMicros(() -> {
            for (int p : positions) {
                sink += linkedList.get(p);
            }
        }));
        System.out.println("  full iteration  ArrayList: " + bestMicros(() -> {
            for (int value : arrayList) {
                sink += value;
            }
        }) + "   LinkedList: " + bestMicros(() -> {
            for (int value : linkedList) {
                sink += value;
            }
        }));
    }

    public static void main(String[] args) {
        List<String> registrations = new ArrayList<>(List.of("anna", "boris", "anna", "carl", "boris", "dina"));
        System.out.println("order:       " + registrations);

        registrations.add(2, "eva");
        System.out.println("insert(2):   " + registrations);

        registrations.remove(0);
        System.out.println("remove(0):   " + registrations);

        registrations.remove("boris");
        System.out.println("remove(obj): " + registrations + " (only the first match is removed)");

        System.out.println("duplicates:  " + duplicates(List.of("anna", "boris", "anna", "carl", "boris", "anna")));
        System.out.println();

        compare(50_000);
        System.out.println();
        System.out.println("This is a rough measurement, not a microbenchmark: the result only shows the trend.");
    }
}
