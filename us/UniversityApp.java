
package us;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class UniversityApp extends JFrame {
    private DatabaseManager dbManager;
    private JTextField teacherNameField, teacherAgeField, courseNameField, courseHoursField, courseTeacherIdField;
    private JTextArea outputArea;

    public UniversityApp() {
        dbManager = new DatabaseManager(new DatabaseConnector());

        setTitle("Университетская система");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Установка фона
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(173, 216, 230)); // Светло-голубой фон
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        teacherNameField = new JTextField(20);
        teacherAgeField = new JTextField(5);
        courseNameField = new JTextField(20);
        courseHoursField = new JTextField(5);
        courseTeacherIdField = new JTextField(5);
        outputArea = new JTextArea(15, 50);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setBackground(new Color(240, 248, 255)); // Цвет фона для текстовой области
        outputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Вывод"));

        // Кнопки
        JButton addTeacherButton = createButton("Добавить преподавателя");
        JButton removeTeacherButton = createButton("Удалить преподавателя");
        JButton updateTeacherButton = createButton("Редактировать преподавателя");
        JButton showTeachersButton = createButton("Показать всех преподавателей");

        JButton addCourseButton = createButton("Добавить курс");
        JButton removeCourseButton = createButton("Удалить курс");
        JButton updateCourseButton = createButton("Редактировать курс");
        JButton showCoursesButton = createButton("Показать все курсы");

        // Добавление компонентов на панель
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("ФИО преподавателя: "), gbc);
        gbc.gridx = 1;
        mainPanel.add(teacherNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Возраст: "), gbc);
        gbc.gridx = 1;
        mainPanel.add(teacherAgeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(addTeacherButton, gbc);
        gbc.gridx = 1;
        mainPanel.add(removeTeacherButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(updateTeacherButton, gbc);
        gbc.gridx = 1;
        mainPanel.add(showTeachersButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Название курса: "), gbc);
        gbc.gridx = 1;
        mainPanel.add(courseNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Часы курса: "), gbc);
        gbc.gridx = 1;
        mainPanel.add(courseHoursField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("ID преподавателя для курса: "), gbc);
        gbc.gridx = 1;
        mainPanel.add(courseTeacherIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(addCourseButton, gbc);
        gbc.gridx = 1;
        mainPanel.add(removeCourseButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(updateCourseButton, gbc);
        gbc.gridx = 1;
        mainPanel.add(showCoursesButton, gbc);

        // Добавление основной панели и текстовой области на JFrame
        add(mainPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);



        addTeacherButton.addActionListener(e -> addTeacher());
        removeTeacherButton.addActionListener(e -> removeTeacher());
        updateTeacherButton.addActionListener(e -> updateTeacher());
        showTeachersButton.addActionListener(e -> showTeachers());

        addCourseButton.addActionListener(e -> addCourse());
        removeCourseButton.addActionListener(e -> removeCourse());
        updateCourseButton.addActionListener(e -> updateCourse());
        showCoursesButton.addActionListener(e -> showCourses());

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(250, 30));
        button.setBackground(new Color(135, 206, 250)); // Голубой цвет кнопки
        button.setForeground(Color.BLACK);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        return button;
    }

    private void addTeacher() {
        String name = teacherNameField.getText();
        int age;
        try {
            age = Integer.parseInt(teacherAgeField.getText());
            dbManager.addTeacher(new Teacher(0, name, age));
            outputArea.append("Преподаватель " + name + " добавлен.\n");
            teacherNameField.setText("");
            teacherAgeField.setText("");
        } catch (NumberFormatException | SQLException e) {
            outputArea.append("Ошибка при добавлении преподавателя: " + e.getMessage() + "\n");
        }
    }

    private void removeTeacher() {
        String idStr = JOptionPane.showInputDialog(this, "Введите ID преподавателя для удаления:");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                dbManager.removeTeacher(id);
                outputArea.append("Преподаватель с ID " + id + " удален.\n");
            } catch (NumberFormatException e) {
                outputArea.append("Ошибка: введите корректный ID преподавателя.\n");
            } catch (SQLException e) {
                outputArea.append("Ошибка при удалении преподавателя: " + e.getMessage() + "\n");
            }
        }
    }

    private void updateTeacher() {
        String idStr = JOptionPane.showInputDialog(this, "Введите ID преподавателя для редактирования:");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                String newName = JOptionPane.showInputDialog(this, "Введите новое имя преподавателя:");
                String newAgeStr = JOptionPane.showInputDialog(this, "Введите новый возраст преподавателя:");

                int newAge = Integer.parseInt(newAgeStr);

                Teacher updatedTeacher = new Teacher(id, newName, newAge);
                dbManager.updateTeacher(updatedTeacher);
                outputArea.append("Преподаватель с ID " + id + " обновлен.\n");
            } catch (NumberFormatException e) {
                outputArea.append("Ошибка: введите корректные данные.\n");
            } catch (SQLException e) {
                outputArea.append("Ошибка при обновлении преподавателя: " + e.getMessage() + "\n");
            }
        }
    }

    private void showTeachers() {
        try {
            List<Teacher> teachers = dbManager.getAllTeachers();
            outputArea.setText("Список преподавателей:\n");
            for (Teacher teacher : teachers) {
                outputArea.append(teacher + "\n");
            }
        } catch (SQLException e) {
            outputArea.append("Ошибка при получении преподавателей: " + e.getMessage() + "\n");
        }
    }

    private void addCourse() {
        String name = courseNameField.getText();
        int hours;
        int teacherId;
        try {
            hours = Integer.parseInt(courseHoursField.getText());
            teacherId = Integer.parseInt(courseTeacherIdField.getText());
            dbManager.addCourse(new Course(0, name, hours, teacherId));
            outputArea.append("Курс '" + name + "' добавлен.\n");
            courseNameField.setText("");
            courseHoursField.setText("");
            courseTeacherIdField.setText("");
        } catch (NumberFormatException | SQLException e) {
            outputArea.append("Ошибка при добавлении курса: " + e.getMessage() + "\n");
        }
    }

    private void removeCourse() {
        String idStr = JOptionPane.showInputDialog(this, "Введите ID курса для удаления:");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                dbManager.removeCourse(id);
                outputArea.append("Курс с ID " + id + " удален.\n");
            } catch (NumberFormatException e) {
                outputArea.append("Ошибка: введите корректный ID курса.\n");
            } catch (SQLException e) {
                outputArea.append("Ошибка при удалении курса: " + e.getMessage() + "\n");
            }
        }
    }

    private void updateCourse() {
        String idStr = JOptionPane.showInputDialog(this, "Введите ID курса для редактирования:");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                String newName = JOptionPane.showInputDialog(this, "Введите новое название курса:");
                String newHoursStr = JOptionPane.showInputDialog(this, "Введите новое количество часов курса:");
                String newTeacherIdStr = JOptionPane.showInputDialog(this, "Введите новый ID преподавателя:");

                int newHours = Integer.parseInt(newHoursStr);
                int newTeacherId = Integer.parseInt(newTeacherIdStr);

                Course updatedCourse = new Course(id, newName, newHours, newTeacherId);
                dbManager.updateCourse(updatedCourse);
                outputArea.append("Курс с ID " + id + " обновлен.\n");
            } catch (NumberFormatException e) {
                outputArea.append("Ошибка: введите корректные данные.\n");
            } catch (SQLException e) {
                outputArea.append("Ошибка при обновлении курса: " + e.getMessage() + "\n");
            }
        }
    }

    private void showCourses() {
        try {
            List<Course> courses = dbManager.getAllCourses();
            outputArea.setText("Список курсов:\n");
            for (Course course : courses) {
                outputArea.append(course + "\n");
            }
        } catch (SQLException e) {
            outputArea.append("Ошибка при получении курсов: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UniversityApp::new);
    }
}
