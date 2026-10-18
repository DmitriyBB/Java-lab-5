package task10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        GoodCourse good = new GoodCourse(1, "Java");
        Set<GoodCourse> goodSet = new HashSet<>();
        Map<GoodCourse, String> goodMap = new HashMap<>();
        goodSet.add(good);
        goodMap.put(good, "room 1");
        good.setTitle("Java advanced");
        System.out.println("hash by id only, title changed:");
        System.out.println("  set.contains(same object)  = " + goodSet.contains(good));
        System.out.println("  set.contains(new id=1)     = " + goodSet.contains(new GoodCourse(1, "other")));
        System.out.println("  map.get                    = " + goodMap.get(good));

        BadCourse bad = new BadCourse(1, "Java");
        Set<BadCourse> badSet = new HashSet<>();
        Map<BadCourse, String> badMap = new HashMap<>();
        badSet.add(bad);
        badMap.put(bad, "room 1");
        bad.setTitle("Java advanced");
        System.out.println("title is part of hashCode, title changed:");
        System.out.println("  set.contains(same object)  = " + badSet.contains(bad));
        System.out.println("  map.get                    = " + badMap.get(bad));
        System.out.println("  set.size()                 = " + badSet.size() + " (element is still inside)");
        System.out.println("  iteration finds it         = " + badSet.iterator().next());
        System.out.println("  remove(same object)        = " + badSet.remove(bad) + " (cannot be removed)");
        badSet.add(bad);
        System.out.println("  add(same object) again     -> size " + badSet.size() + " (duplicate in the set)");
    }
}
