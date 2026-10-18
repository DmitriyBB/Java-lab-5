package task12;

import model.Course;
import model.CourseCode;
import model.CourseId;

import java.util.List;

public class Main {

    static Course course(long id, String code, String title) {
        return new Course(new CourseId(id), new CourseCode(code), title);
    }

    public static void main(String[] args) {
        Repository<CourseId, Course> repository = new InMemoryRepository<>(Course::id);

        repository.save(course(1, "JAVA-101", "Java"));
        repository.save(course(2, "SQL-200", "SQL"));
        System.out.println("findAll:         " + titles(repository.findAll()));

        repository.save(course(1, "JAVA-101", "Java v2"));
        System.out.println("save same id:    " + titles(repository.findAll()) + " (replaced, position kept)");

        System.out.println("findById(2):     " + repository.findById(new CourseId(2)).map(Course::title).orElse("-"));
        System.out.println("findById(9):     " + repository.findById(new CourseId(9)).map(Course::title).orElse("-"));
        System.out.println("deleteById(2):   " + repository.deleteById(new CourseId(2)));
        System.out.println("deleteById(2):   " + repository.deleteById(new CourseId(2)));

        List<Course> snapshot = repository.findAll();
        try {
            snapshot.clear();
        } catch (UnsupportedOperationException e) {
            System.out.println("findAll result is read-only: UnsupportedOperationException");
        }
        System.out.println("repository size: " + repository.findAll().size());

        try {
            repository.save(null);
        } catch (NullPointerException e) {
            System.out.println("save(null):      NullPointerException " + e.getMessage());
        }
        try {
            repository.findById(null);
        } catch (NullPointerException e) {
            System.out.println("findById(null):  NullPointerException " + e.getMessage());
        }
    }

    static List<String> titles(List<Course> courses) {
        return courses.stream().map(Course::title).toList();
    }
}
