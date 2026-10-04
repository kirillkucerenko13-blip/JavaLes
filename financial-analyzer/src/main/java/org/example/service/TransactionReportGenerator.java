package org.example.service;

import org.example.model.Transaction;

import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {

    private TransactionReportGenerator() {
    }

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("\n--- 10 найбільших витрат ---");
        for (Transaction expense : topExpenses) {
            System.out.printf("%s: %.2f грн (%s)\n", expense.getDescription(), expense.getAmount(), expense.getDate());
        }
    }

    public static void printMinMaxExpensesReport(Transaction minExpense, Transaction maxExpense) {
        System.out.println("\n--- Аналіз витрат за період ---");
        if (maxExpense != null) {
            System.out.printf("Найбільша витрата: %s на суму %.2f грн (%s)\n",
                    maxExpense.getDescription(), maxExpense.getAmount(), maxExpense.getDate());
        }
        if (minExpense != null) {
            System.out.printf("Найменша витрата: %s на суму %.2f грн (%s)\n",
                    minExpense.getDescription(), minExpense.getAmount(), minExpense.getDate());
        }
    }

    public static void printVisualCategoryReport(Map<String, Double> categoryExpenses) {
        System.out.println("\n--- Сумарні витрати по категоріях (* = 1000 грн) ---");
        categoryExpenses.forEach((category, sum) -> {
            int stars = (int) Math.round(sum / 1000.0);
            String bar = "*".repeat(Math.max(1, stars));
            System.out.printf("%-20s | %-15s (%.2f грн)\n", category, bar, sum);
        });
    }

    public static void printVisualMonthlyReport(Map<String, Double> monthlyExpenses) {
        System.out.println("\n--- Сумарні витрати по місяцях (* = 1000 грн) ---");
        monthlyExpenses.forEach((month, sum) -> {
            int stars = (int) Math.round(sum / 1000.0);
            String bar = "*".repeat(Math.max(1, stars));
            System.out.printf("%-10s | %-15s (%.2f грн)\n", month, bar, sum);
        });
    }
}