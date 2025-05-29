package prakt2;

import java.util.Stack;

public class Model {

    public double calculate(String input) throws Exception {
        input = input.replaceAll("\s+", "");
        checkParentheses(input);
        if (countTerms(input) > 15) {
            throw new Exception("Количество слагаемых превышает 15");
        }

        Stack<Double> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();

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
                operators.push("(");
                i++;
            } else if (currentChar == ')') {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                if (operators.isEmpty()) {
                    throw new Exception("Несоответствующие скобки");
                }
                operators.pop();
                i++;
            } else if (isOperator(currentChar)) {
                while (!operators.isEmpty() && precedence(String.valueOf(currentChar)) <= precedence(operators.peek())) {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.push(String.valueOf(currentChar));
                i++;
            } else if (currentChar == '!') {
                i++;
                if (numbers.isEmpty()) {
                    throw new Exception("Нет числа для факториала");
                }
                numbers.push(factorial(numbers.pop()));
            } else if (input.startsWith("log(", i)) {
                i += 4;
                double num = parseNumber(input, i);
                numbers.push(Math.log(num) / Math.log(2));
                i += String.valueOf(num).length() + 1;
            } else if (input.startsWith("exp(", i)) {
                i += 4;
                double num = parseNumber(input, i);
                numbers.push(Math.exp(num));
                i += String.valueOf(num).length() + 1;
            } else if (input.startsWith("^", i)) {
                i++;
                double base = numbers.pop();
                double exponent = parseNumber(input, i);
                numbers.push(Math.pow(base, exponent));
                i += String.valueOf(exponent).length();
            } else {
                throw new Exception("Неизвестный символ: " + currentChar);
            }
        }

        while (!operators.isEmpty()) {
            numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private void checkParentheses(String input) throws Exception {
        int balance = 0;
        for (char ch : input.toCharArray()) {
            if (ch == '(') balance++;
            if (ch == ')') balance--;
            if (balance < 0) throw new Exception("Несоответствующие скобки");
        }
        if (balance != 0) throw new Exception("Несоответствующие скобки");
    }

    private int countTerms(String input) {
        String[] terms = input.split("[+/-]");
        return terms.length;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private int precedence(String operator) {
        switch (operator) {
            case "+": case "-": return 1;
            case "*": case "/": return 2;
            case "^": return 3;
            default: return -1;
        }
    }

    private double applyOperation(String operator, double b, double a) throws Exception {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) throw new Exception("Деление на ноль");
                return a / b;
            case "^": return Math.pow(a, b);
            default: throw new Exception("Неизвестный оператор: " + operator);
        }
    }

    private double factorial(double num) throws Exception {
        if (num < 0 || num != Math.floor(num)) throw new Exception("Факториал определен только для неотрицательных целых чисел");
        double result = 1;
        for (int i = 2; i <= num; i++) result *= i;
        return result;
    }

    private double parseNumber(String input, int index) {
        StringBuilder number = new StringBuilder();
        while (index < input.length() && (Character.isDigit(input.charAt(index)) || input.charAt(index) == '.')) {
            number.append(input.charAt(index));
            index++;
        }
        return Double.parseDouble(number.toString());
    }
}

