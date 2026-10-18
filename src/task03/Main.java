package task03;

import model.Course;
import model.CourseCode;
import model.CourseId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static Map<CourseId, Course> buildIndex(List<Course> courses) {
        Map<CourseId, Course> index = new HashMap<>();
        for (Course course : courses) {
            Course previous = index.putIfAbsent(course.id(), course);
            if (previous != null) {
                throw new IllegalArgumentException("Duplicate course id " + course.id().value()
                        + ": '" + previous.title() + "' and '" + course.title() + "'");
            }
        }
        return index;
    }

    static Course course(long id, String code, String title) {
        return new Course(new CourseId(id), new CourseCode(code), title);
    }

    public static void main(String[] args) {
        Course java = course(1, "JAVA-101", "Java basics");
        Course sql = course(2, "SQL-200", "SQL");
        Course clash = course(1, "GO-300", "Go");

        Map<CourseId, Course> index = buildIndex(List.of(java, sql));
        System.out.println("index: " + index.keySet());

        try {
            buildIndex(List.of(java, sql, clash));
        } catch (IllegalArgumentException e) {
            System.out.println("duplicate id: " + e.getMessage());
        }

        CourseId id1 = new CourseId(1);
        CourseId id9 = new CourseId(9);
        System.out.println();
        System.out.println("get(1)         = " + index.get(id1).title());
        System.out.println("get(9)         = " + index.get(id9));
        System.out.println("containsKey(9) = " + index.containsKey(id9));

        Map<CourseId, Course> demo = new HashMap<>(index);
        System.out.println("putIfAbsent(1, other) -> " + demo.putIfAbsent(id1, clash).title() + " (kept old, returns old)");
        System.out.println("putIfAbsent(9, other) -> " + demo.putIfAbsent(id9, clash) + " (inserted, returns null)");

        demo = new HashMap<>(index);
        Course created = demo.computeIfAbsent(id9, id -> course(id.value(), "NEW-009", "Created lazily"));
        System.out.println("computeIfAbsent(9)    -> " + created.title() + " (function runs only when key is absent)");
        Course existing = demo.computeIfAbsent(id1, id -> {
            throw new IllegalStateException("not called");
        });
        System.out.println("computeIfAbsent(1)    -> " + existing.title() + " (function was not called)");
    }
}
