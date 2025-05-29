package prakt2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.addCalculateListener(new CalculateListener());
        view.addClearListener(new ClearListener());
    }

    class CalculateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input = view.getInput();
            try {
                double result = model.calculate(input);
                view.setResult(String.valueOf(result));
            } catch (Exception ex) {
                view.showError("Invalid expression");
            }
        }
    }

    class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.clearInput();
            view.setResult("Result: ");
        }
    }
}