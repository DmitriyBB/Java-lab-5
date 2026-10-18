package task08;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {

    static <T extends Comparable<? super T>> T max(Collection<? extends T> items) {
        Iterator<? extends T> iterator = items.iterator();
        if (!iterator.hasNext()) {
            throw new NoSuchElementException("collection is empty");
        }
        T best = iterator.next();
        while (iterator.hasNext()) {
            T candidate = iterator.next();
            if (candidate.compareTo(best) > 0) {
                best = candidate;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("Integer: " + max(List.of(3, 9, 4)));
        System.out.println("String:  " + max(List.of("pear", "apple", "zebra", "mango")));
        System.out.println("Money:   " + max(List.of(new Money(500), new Money(1200), new Money(99))));
        System.out.println("Dog:     " + max(List.of(new Dog(12), new Dog(30), new Dog(7))));
        try {
            max(List.<Integer>of());
        } catch (NoSuchElementException e) {
            System.out.println("empty:   " + e.getMessage());
        }
    }
}
