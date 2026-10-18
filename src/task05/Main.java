package task05;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class Main {

    static List<String> names() {
        return new ArrayList<>(List.of("anna", "boris", "anton", "carl"));
    }

    public static void main(String[] args) {
        List<String> broken = names();
        try {
            for (String name : broken) {
                if (name.startsWith("a")) {
                    broken.remove(name);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("enhanced for + list.remove -> ConcurrentModificationException");
        }

        List<String> viaIterator = names();
        Iterator<String> iterator = viaIterator.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().startsWith("a")) {
                iterator.remove();
            }
        }
        System.out.println("Iterator.remove: " + viaIterator);

        List<String> viaRemoveIf = names();
        viaRemoveIf.removeIf(name -> name.startsWith("a"));
        System.out.println("removeIf:        " + viaRemoveIf);

        List<String> viaCopy = names();
        for (String name : new ArrayList<>(viaCopy)) {
            if (name.startsWith("a")) {
                viaCopy.remove(name);
            }
        }
        System.out.println("copy + remove:   " + viaCopy + " (works, but copies the whole list)");
    }
}
