package task11;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

    record Player(String name, int score) {}

    public static void main(String[] args) {
        Map<Long, String> catalog = new HashMap<>();
        catalog.put(1L, "Java");
        catalog.put(2L, "SQL");
        System.out.println("1. catalog by id (Map/HashMap):           get(2) = " + catalog.get(2L));

        TreeSet<Player> leaderboard = new TreeSet<>(
                Comparator.comparingInt(Player::score).reversed().thenComparing(Player::name));
        leaderboard.add(new Player("anna", 80));
        leaderboard.add(new Player("boris", 95));
        leaderboard.add(new Player("carl", 80));
        System.out.println("2. leaderboard (NavigableSet/TreeSet):    " + leaderboard);

        Queue<String> queue = new ArrayDeque<>(List.of("anna", "boris"));
        queue.offer("carl");
        System.out.println("3. queue (Queue/ArrayDeque):              poll = " + queue.poll());

        Set<String> tags = new LinkedHashSet<>();
        for (String tag : List.of("java", "sql", "java", "oop")) {
            tags.add(tag);
        }
        System.out.println("4. unique tags in order (Set/LinkedHashSet): " + tags);

        TreeMap<Integer, String> prices = new TreeMap<>();
        prices.put(10, "A");
        prices.put(25, "B");
        prices.put(40, "C");
        prices.put(80, "D");
        System.out.println("5. range 20..50 (NavigableMap/TreeMap):   " + prices.subMap(20, true, 50, true));

        List<String> items = new ArrayList<>(List.of("a", "b", "c", "d"));
        System.out.println("6. frequent index access (List/ArrayList): get(2) = " + items.get(2));
    }
}
