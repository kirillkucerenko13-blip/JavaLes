package org.example;

import org.example.model.Transaction;
import org.example.reader.TransactionCSVReader;
import org.example.service.TransactionAnalyzer;
import org.example.service.TransactionReportGenerator;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
      //String filePath = "file:///" + System.getProperty("user.dir").replace("\\", "/") + "/pr2.csv";
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "12-2023";
        int count = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, count);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        var maxExp = TransactionAnalyzer.findMaxExpenseForPeriod(transactions, "01-12-2023", "31-12-2023").orElse(null);
        var minExp = TransactionAnalyzer.findMinExpenseForPeriod(transactions, "01-12-2023", "31-12-2023").orElse(null);
        TransactionReportGenerator.printMinMaxExpensesReport(minExp, maxExp);

        Map<String, Double> categoryExpenses = TransactionAnalyzer.calculateExpensesByCategory(transactions);
        TransactionReportGenerator.printVisualCategoryReport(categoryExpenses);

        Map<String, Double> monthlyExpenses = TransactionAnalyzer.calculateExpensesByMonth(transactions);
        TransactionReportGenerator.printVisualMonthlyReport(monthlyExpenses);
    }
}