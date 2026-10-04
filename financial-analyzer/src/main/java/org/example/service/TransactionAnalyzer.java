package org.example.service;

import org.example.model.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private TransactionAnalyzer() {

    }

    public static double calculateTotalBalance(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), DATE_FORMATTER);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static Optional<Transaction> findMaxExpenseForPeriod(List<Transaction> transactions, String startDateStr, String endDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(endDateStr, DATE_FORMATTER);

        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate d = LocalDate.parse(t.getDate(), DATE_FORMATTER);
                    return (!d.isBefore(startDate) && !d.isAfter(endDate));
                })
                .min(Comparator.comparingDouble(Transaction::getAmount));
    }

    public static Optional<Transaction> findMinExpenseForPeriod(List<Transaction> transactions, String startDateStr, String endDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(endDateStr, DATE_FORMATTER);

        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate d = LocalDate.parse(t.getDate(), DATE_FORMATTER);
                    return (!d.isBefore(startDate) && !d.isAfter(endDate));
                })
                .max(Comparator.comparingDouble(Transaction::getAmount));
    }

    public static Map<String, Double> calculateExpensesByCategory(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        Transaction::getDescription,
                        Collectors.summingDouble(t -> Math.abs(t.getAmount()))
                ));
    }

    public static Map<String, Double> calculateExpensesByMonth(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        t -> LocalDate.parse(t.getDate(), DATE_FORMATTER).format(DateTimeFormatter.ofPattern("MM-yyyy")),
                        LinkedHashMap::new,
                        Collectors.summingDouble(t -> Math.abs(t.getAmount()))
                ));
    }
}