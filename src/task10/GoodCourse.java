package task10;

public class GoodCourse {

    private final long id;
    private String title;

    public GoodCourse(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof GoodCourse other && id == other.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public String toString() {
        return "GoodCourse(" + id + ", " + title + ")";
    }
}
