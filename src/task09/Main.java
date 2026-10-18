package task09;

import model.Course;
import model.CourseCode;
import model.CourseId;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static <T> void copy(List<? extends T> source, List<? super T> target) {
        for (T item : source) {
            target.add(item);
        }
    }

    static Course course(long id, String code) {
        return new Course(new CourseId(id), new CourseCode(code), "title " + id);
    }

    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3);
        List<Number> numbers = new ArrayList<>(List.of(0.5));
        copy(integers, numbers);
        System.out.println("List<Integer> -> List<Number>: " + numbers);

        List<Course> courses = List.of(course(1, "JAVA-101"), course(2, "SQL-200"));
        List<Object> objects = new ArrayList<>();
        copy(courses, objects);
        System.out.println("List<Course> -> List<Object>:  " + objects.size() + " items");

        List<? extends Course> producer = courses;
        Course first = producer.get(0);
        System.out.println("read from ? extends Course: " + first.code().value());

        List<? super Course> consumer = new ArrayList<Object>();
        consumer.add(course(3, "GO-300"));
        Object read = consumer.get(0);
        System.out.println("read from ? super Course gives only Object: " + read);
    }
}
