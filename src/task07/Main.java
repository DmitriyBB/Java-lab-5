package task07;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("anna", "boris", "carl"));

        System.out.println("firstOrThrow: " + Util.firstOrThrow(names));
        try {
            Util.firstOrThrow(List.of());
        } catch (NoSuchElementException e) {
            System.out.println("firstOrThrow(empty): " + e.getMessage());
        }

        Util.swap(names, 0, 2);
        System.out.println("swap(0, 2): " + names);
        try {
            Util.swap(names, 0, 5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("swap(0, 5): " + e.getMessage() + ", list unchanged: " + names);
        }

        System.out.println("indexBy(length): " + Util.indexBy(List.of("a", "bb", "ccc"), String::length));
        try {
            Util.indexBy(names, String::length);
        } catch (IllegalStateException e) {
            System.out.println("indexBy conflict: " + e.getMessage());
        }
    }
}
