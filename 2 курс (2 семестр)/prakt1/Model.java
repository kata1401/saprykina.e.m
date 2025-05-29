package prakt1;

import java.util.Stack;

public class Model {

    public double calculate(String input) throws Exception {
        input = input.replaceAll("\\s+", "");
        checkParentheses(input);

        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int i = 0;
        while (i < input.length()) {
            char currentChar = input.charAt(i);

            if (Character.isDigit(currentChar) || (currentChar == '-' && (i == 0 || !Character.isDigit(input.charAt(i - 1))))) {
                StringBuilder number = new StringBuilder();
                if (currentChar == '-') {
                    number.append(currentChar);
                    i++;
                }
                while (i < input.length() && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
                    number.append(input.charAt(i));
                    i++;
                }
                numbers.push(Double.parseDouble(number.toString()));
            } else if (currentChar == '(') {
                operators.push(currentChar);
                i++;
            } else if (currentChar == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                if (operators.isEmpty()) {
                    throw new Exception("Несоответствующие скобки");
                }
                operators.pop();
                i++;
            } else if (isOperator(currentChar)) {
                while (!operators.isEmpty() && precedence(currentChar) <= precedence(operators.peek())) {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.push(currentChar);
                i++;
            } else {
                throw new Exception("Недопустимый символ: " + currentChar);
            }
        }


        while (!operators.isEmpty()) {
            numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    private double applyOperation(char operator, double b, double a) throws Exception {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) throw new Exception("Деление на ноль");
                return a / b;
            case '^':
                return Math.pow(a, b);
            default:
                throw new Exception("Неверный оператор: " + operator);
        }
    }

    private void checkParentheses(String input) throws Exception {
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    throw new Exception("Несоответствующие скобки");
                }
                stack.pop();
            }
        }
        if (!stack.isEmpty()) {
            throw new Exception("Несоответствующие скобки");
        }
    }
}