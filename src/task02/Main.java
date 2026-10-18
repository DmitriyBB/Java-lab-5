package task02;

import model.CourseCode;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Main {

    static <T> List<T> unique(List<T> items) {
        return new ArrayList<>(new LinkedHashSet<>(items));
    }

    public static void main(String[] args) {
        List<CourseCode> codes = List.of(
                new CourseCode("JAVA-101"), new CourseCode("SQL-200"),
                new CourseCode("JAVA-101"), new CourseCode("GO-300"), new CourseCode("SQL-200"));
        System.out.println("input:        " + codes);
        System.out.println("unique:       " + unique(codes));

        List<BadCode> badCodes = List.of(new BadCode("JAVA-101"), new BadCode("SQL-200"), new BadCode("JAVA-101"));
        System.out.println("no equals:    " + unique(badCodes) + "  <- duplicate stays");

        Set<CourseCode> set = new LinkedHashSet<>(codes);
        System.out.println("contains new CourseCode(\"GO-300\"): " + set.contains(new CourseCode("GO-300")));
    }
}
