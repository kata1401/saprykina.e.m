package universitySystem;

import java.util.ArrayList;
import java.util.List;

class Teacher {
    private String fullName;
    private int age;
    private List<Course> courses;

    public Teacher(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
        this.courses = new ArrayList<>();
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public void editCourse(Course oldCourse, Course newCourse) {
        int index = courses.indexOf(oldCourse);
        if (index != -1) {
            courses.set(index, newCourse);
        }
    }
}