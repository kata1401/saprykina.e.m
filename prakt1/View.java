package prakt1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    private JFrame frame;
    private JTextField inputField;
    private JButton calculateButton;
    private JButton clearButton;
    private JLabel resultLabel;

    public View() {

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());


        inputField = new JTextField(20);
        inputField.setBackground(new Color(173, 216, 230));
        inputField.setForeground(Color.BLACK);

        calculateButton = new JButton("Calculate");
        calculateButton.setBackground(new Color(70, 130, 180));
        calculateButton.setForeground(Color.WHITE);

        clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(70, 130, 180));
        clearButton.setForeground(Color.WHITE);
        resultLabel = new JLabel("Result: ");
        resultLabel.setForeground(new Color(0, 102, 204));


        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 248, 255));
        panel.add(inputField);
        panel.add(calculateButton);
        panel.add(clearButton);
        panel.add(resultLabel);


        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public String getInput() {
        String input = inputField.getText();
        if (input.isEmpty()) {
            return input;
        }

        char firstChar = input.charAt(0);
        if (firstChar == '-' || firstChar == '+') {

            return input;
        }

        return input;
    }
    public void setResult(String result) {
        resultLabel.setText("Result: " + result);
    }
    public void addCalculateListener(ActionListener listener) {
        calculateButton.addActionListener(listener);
    }
    public void addClearListener(ActionListener listener) {
        clearButton.addActionListener(listener);
    }
    public void clearInput() {
        inputField.setText("");
    }
    public void showError(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
