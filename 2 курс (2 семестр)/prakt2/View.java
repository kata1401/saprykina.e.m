//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package prakt2;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View {
    private JFrame frame = new JFrame("Calculator");
    private JTextField inputField;
    private JButton calculateButton;
    private JButton clearButton;
    private JLabel resultLabel;

    public View() {
        this.frame.setDefaultCloseOperation(3);
        this.frame.setLayout(new FlowLayout());
        this.inputField = new JTextField(20);
        this.inputField.setBackground(new Color(173, 216, 230));
        this.inputField.setForeground(Color.BLACK);
        this.calculateButton = new JButton("Calculate");
        this.calculateButton.setBackground(new Color(70, 130, 180));
        this.calculateButton.setForeground(Color.WHITE);
        this.clearButton = new JButton("Clear");
        this.clearButton.setBackground(new Color(70, 130, 180));
        this.clearButton.setForeground(Color.WHITE);
        this.resultLabel = new JLabel("Result: ");
        this.resultLabel.setForeground(new Color(0, 102, 204));
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 248, 255));
        panel.add(this.inputField);
        panel.add(this.calculateButton);
        panel.add(this.clearButton);
        panel.add(this.resultLabel);
        this.frame.getContentPane().add(panel);
        this.frame.pack();
        this.frame.setLocationRelativeTo((Component)null);
        this.frame.setVisible(true);
    }

    public String getInput() {
        String input = this.inputField.getText();
        if (input.isEmpty()) {
            return input;
        } else {
            char firstChar = input.charAt(0);
            return firstChar != '-' && firstChar != '+' ? input : input;
        }
    }

    public void setResult(String result) {
        this.resultLabel.setText("Result: " + result);
    }

    public void addCalculateListener(ActionListener listener) {
        this.calculateButton.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        this.clearButton.addActionListener(listener);
    }

    public void clearInput() {
        this.inputField.setText("");
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this.frame, message);
    }
}
