package org.example;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("=== КОНСОЛЬНИЙ КАЛЬКУЛЯТОР ===");
        System.out.println("Доступні операції: +, -, *, /, sqrt (квадратний корінь)");

        try {
            System.out.print("Введіть перше число: ");
            double num1 = scanner.nextDouble();

            System.out.print("Введіть операцію (+, -, *, /, sqrt): ");
            String operation = scanner.next();

            double num2 = 0;
            if (!operation.equalsIgnoreCase("sqrt")) {
                System.out.print("Введіть друге число: ");
                num2 = scanner.nextDouble();
            }

            double result = 0;
            switch (operation) {
                case "+":
                    result = calculator.add(num1, num2);
                    break;
                case "-":
                    result = calculator.subtract(num1, num2);
                    break;
                case "*":
                    result = calculator.multiply(num1, num2);
                    break;
                case "/":
                    result = calculator.divide(num1, num2);
                    break;
                case "sqrt":
                    result = calculator.squareRoot(num1);
                    break;
                default:
                    throw new InvalidInputException("Невідома операція: '" + operation + "'");
            }

            System.out.println("Результат: " + result);

        } catch (InputMismatchException e) {
            System.out.println("Помилка: Введено невірні дані (очікувалося числове значення замість тексту).");
        } catch (ArithmeticException e) {
            System.out.println("Арифметична помилка: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Користувацька помилка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Сталася непередбачена помилка: " + e.getMessage());
        } finally {
            System.out.println("[FINALLY] Завершення обробки запиту.");
            scanner.close();
        }
    }
}