package task10;

import java.util.Objects;

public class BadCourse {

    private final long id;
    private String title;

    public BadCourse(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof BadCourse other && id == other.id && Objects.equals(title, other.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "BadCourse(" + id + ", " + title + ")";
    }
}
