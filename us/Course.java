package us;

public class Course {
    private int id;
    private String name;
    private int hours;
    private int teacherId;

    public Course(int id, String name, int hours, int teacherId) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.teacherId = teacherId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public int getTeacherId() {
        return teacherId;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Название: " + name + ", Часы: " + hours + ", Преподаватель ID: " + teacherId;
    }
}
