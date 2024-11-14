package us;

public class Teacher {
    private int id;
    private String name;
    private int age;

    public Teacher(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", ФИО: " + name + ", Возраст: " + age;
    }
}
