package universitySystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String name;
    private int hours;

    public Course(String name, int hours) {
        this.name = name;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    @Override
    public String toString() {
        return name + " (" + hours + " часов)";
    }
}


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

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UniversitySystem system = new UniversitySystem();

        while (true) {
            System.out.println("1. Добавить преподавателя");
            System.out.println("2. Добавить курс к преподавателю");
            System.out.println("3. Удалить курс у преподавателя");
            System.out.println("4. Показать всех преподавателей");
            System.out.println("5. Показать курсы преподавателя");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1:
                    String name;
                    while (true) {
                        System.out.print("Введите ФИО преподавателя: ");
                        name = scanner.nextLine();
                        if (isValidFullName(name)) {
                            break;
                        } else {
                            System.out.println("Ошибка: ФИО не может содержать цифры или специальные символы. Попробуйте еще раз.");
                        }
                    }
                    int age = -1;
                    while (true) {
                        System.out.print("Введите возраст преподавателя: ");
                        String ageInput = scanner.nextLine();
                        if (isValidAge(ageInput)) {
                            age = Integer.parseInt(ageInput);
                            break;
                        } else {
                            System.out.println("Ошибка: Возраст должен быть числом. Попробуйте еще раз.");
                        }
                    }
                    system.addTeacher(new Teacher(name, age));
                    break;

                case 2:
                    System.out.print("Введите ФИО преподавателя: ");
                    String teacherName = scanner.nextLine();
                    Teacher teacher = system.findTeacher(teacherName);
                    if (teacher != null) {
                        System.out.print("Введите название курса: ");
                        String courseName = scanner.nextLine();

                        int hours = -1;
                        while (true) {
                            System.out.print("Введите количество часов: ");
                            String hoursInput = scanner.nextLine();
                            if (isValidHours(hoursInput)) {
                                hours = Integer.parseInt(hoursInput);
                                break;
                            } else {
                                System.out.println("Ошибка: количество часов должно быть числом. Попробуйте еще раз.");
                            }
                        }
                        teacher.addCourse(new Course(courseName, hours));
                    } else {
                        System.out.println("Преподаватель не найден.");
                    }
                    break;

                case 3:
                    System.out.print("Введите ФИО преподавателя: ");
                    teacherName = scanner.nextLine();
                    teacher = system.findTeacher(teacherName);
                    if (teacher != null) {
                        System.out.print("Введите название курса для удаления: ");
                        String courseName = scanner.nextLine();
                        Course courseToRemove = null;
                        for (Course course : teacher.getCourses()) {
                            if (course.getName().equalsIgnoreCase(courseName)) {
                                courseToRemove = course;
                                break;
                            }
                        }
                        if (courseToRemove != null) {
                            teacher.removeCourse(courseToRemove);
                        } else {
                            System.out.println("Курс не найден.");
                        }
                    } else {
                        System.out.println("Преподаватель не найден.");
                    }
                    break;

                case 4:
                    System.out.println("Преподаватели:");
                    for (Teacher t : system.getTeachers()) {
                        System.out.println(t.getFullName() + " (" + t.getAge() + " лет)");
                    }
                    break;
                case 5:
                    System.out.print("Введите ФИО преподавателя: ");
                    teacherName = scanner.nextLine();
                    teacher = system.findTeacher(teacherName);
                    if (teacher != null) {
                        System.out.println("Курсы преподавателя " + teacher.getFullName() + ":");
                        for (Course course : teacher.getCourses()) {
                            System.out.println(course);
                        }
                    } else {
                        System.out.println("Преподаватель не найден.");
                    }
                    break;

                case 0:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный ввод, пожалуйста, попробуйте еще раз.");
            }
        }
    }

    private static boolean isValidFullName(String fullName) {
        return fullName.matches("^[А-Яа-яЁё\\s]+$") || fullName.matches("^[A-Za-z\\s]+$");
    }

    private static boolean isValidAge(String ageInput) {
        return ageInput.matches("\\d+");
    }

    private static boolean isValidHours(String hoursInput) {
        return hoursInput.matches("\\d+");
    }
}
