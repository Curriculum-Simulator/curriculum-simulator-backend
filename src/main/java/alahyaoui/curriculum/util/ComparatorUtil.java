package alahyaoui.curriculum.util;

import java.util.Comparator;

import alahyaoui.curriculum.model.Course;

public class ComparatorUtil {
    public static final Comparator<Course> COURSE_COMPARATOR = Comparator.comparingInt(Course::getQuarter).thenComparing(Course::getId);
}
