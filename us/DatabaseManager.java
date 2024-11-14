package us;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    public void addTeacher(Teacher teacher) throws SQLException {
        String query = "INSERT INTO teachers (name, age) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, teacher.getName());
        statement.setInt(2, teacher.getAge());
        statement.executeUpdate();
    }

    public void removeTeacher(int id) throws SQLException {
        String query = "DELETE FROM teachers WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void updateTeacher(Teacher teacher) throws SQLException {
        String query = "UPDATE teachers SET name = ?, age = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, teacher.getName());
        statement.setInt(2, teacher.getAge());
        statement.setInt(3, teacher.getId());
        statement.executeUpdate();
    }

    public List<Teacher> getAllTeachers() throws SQLException {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM teachers";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            teachers.add(new Teacher(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age")));
        }
        return teachers;
    }

    public void addCourse(Course course) throws SQLException {
        String query = "INSERT INTO courses (name, hours, teacher_id) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, course.getName());
        statement.setInt(2, course.getHours());
        statement.setInt(3, course.getTeacherId());
        statement.executeUpdate();
    }

    public void removeCourse(int id) throws SQLException {
        String query = "DELETE FROM courses WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void updateCourse(Course course) throws SQLException {
        String query = "UPDATE courses SET name = ?, hours = ?, teacher_id = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, course.getName());
        statement.setInt(2, course.getHours());
        statement.setInt(3, course.getTeacherId());
        statement.setInt(4, course.getId());
        statement.executeUpdate();
    }

    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            courses.add(new Course(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("hours"), resultSet.getInt("teacher_id")));
        }
        return courses;
    }
}
