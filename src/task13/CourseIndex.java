package task13;

import model.CourseId;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class CourseIndex {

    private final Map<Tag, Set<CourseId>> coursesByTag = new HashMap<>();
    private final Map<CourseId, Set<Tag>> tagsByCourse = new HashMap<>();

    public void add(CourseId course, Set<Tag> tags) {
        Objects.requireNonNull(course, "course");
        Objects.requireNonNull(tags, "tags");
        remove(course);
        if (tags.isEmpty()) {
            return;
        }
        tagsByCourse.put(course, new LinkedHashSet<>(tags));
        for (Tag tag : tags) {
            coursesByTag.computeIfAbsent(tag, t -> new LinkedHashSet<>()).add(course);
        }
    }

    public void remove(CourseId course) {
        Set<Tag> tags = tagsByCourse.remove(course);
        if (tags == null) {
            return;
        }
        for (Tag tag : tags) {
            Set<CourseId> courses = coursesByTag.get(tag);
            courses.remove(course);
            if (courses.isEmpty()) {
                coursesByTag.remove(tag);
            }
        }
    }

    public Set<CourseId> findByAll(Set<Tag> tags) {
        Objects.requireNonNull(tags, "tags");
        if (tags.isEmpty()) {
            return Set.of();
        }
        Set<CourseId> result = null;
        for (Tag tag : tags) {
            Set<CourseId> courses = coursesByTag.get(tag);
            if (courses == null) {
                return Set.of();
            }
            if (result == null) {
                result = new LinkedHashSet<>(courses);
            } else {
                result.retainAll(courses);
            }
        }
        return result;
    }

    public Set<Tag> tags() {
        return Set.copyOf(coursesByTag.keySet());
    }
}
