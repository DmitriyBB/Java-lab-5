package task13;

import model.CourseId;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    static Set<Tag> tags(String... names) {
        Set<Tag> result = new LinkedHashSet<>();
        for (String name : names) {
            result.add(new Tag(name));
        }
        return result;
    }

    static CourseId id(long value) {
        return new CourseId(value);
    }

    static String sortedTags(CourseIndex index) {
        TreeSet<String> names = new TreeSet<>();
        index.tags().forEach(tag -> names.add(tag.name()));
        return names.toString();
    }

    public static void main(String[] args) {
        CourseIndex index = new CourseIndex();
        index.add(id(1), tags("java", "oop", "beginner"));
        index.add(id(2), tags("java", "streams"));
        index.add(id(3), tags("sql", "beginner"));
        index.add(id(4), tags("java", "oop"));

        System.out.println("tags:                 " + sortedTags(index));
        System.out.println("java:                 " + index.findByAll(tags("java")));
        System.out.println("java + oop:           " + index.findByAll(tags("java", "oop")));
        System.out.println("java + oop + beginner:" + index.findByAll(tags("java", "oop", "beginner")));
        System.out.println("java + sql:           " + index.findByAll(tags("java", "sql")));
        System.out.println("unknown tag:          " + index.findByAll(tags("go")));
        System.out.println("no tags:              " + index.findByAll(tags()));

        index.remove(id(2));
        System.out.println("after remove(2), tags: " + sortedTags(index) + " ('streams' disappeared)");

        index.remove(id(3));
        System.out.println("after remove(3), tags: " + sortedTags(index) + " ('sql' disappeared)");

        index.add(id(1), tags("java"));
        System.out.println("re-add 1 with [java]: " + index.findByAll(tags("oop")) + " for oop, " + sortedTags(index));
    }
}
