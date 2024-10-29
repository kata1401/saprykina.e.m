package universitySystem;

import java.util.ArrayList;
import java.util.List;

class UniversitySystem {
    private List<Teacher> teachers;

    public UniversitySystem() {
        teachers = new ArrayList<>();
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public Teacher findTeacher(String fullName) {
        for (Teacher teacher : teachers) {
            if (teacher.getFullName().equalsIgnoreCase(fullName)) {
                return teacher;
            }
        }
        return null;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }
}
