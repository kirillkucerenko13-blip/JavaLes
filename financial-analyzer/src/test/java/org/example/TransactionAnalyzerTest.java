package org.example;

import org.example.model.Transaction;
import org.example.service.TransactionAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class TransactionAnalyzerTest {

    private List<Transaction> sampleTransactions;

    @BeforeEach
    void setUp() {
        sampleTransactions = Arrays.asList(
                new Transaction("05-12-2023", -450.0, "Сільпо"),
                new Transaction("07-12-2023", -120.0, "Аптека"),
                new Transaction("10-12-2023", 8000.0, "Зарплата"),
                new Transaction("14-12-2023", -3200.0, "Оренда квартири"),
                new Transaction("05-01-2024", -500.0, "Сільпо")
        );
    }

    @Test
    void testCalculateTotalBalance() {
        double balance = TransactionAnalyzer.calculateTotalBalance(sampleTransactions);
        Assertions.assertEquals(3730.0, balance, 0.001, "Баланс розраховано некоректно");
    }

    @Test
    void testCountTransactionsByMonth() {
        int countDec = TransactionAnalyzer.countTransactionsByMonth(sampleTransactions, "12-2023");
        int countJan = TransactionAnalyzer.countTransactionsByMonth(sampleTransactions, "01-2024");

        Assertions.assertEquals(4, countDec);
        Assertions.assertEquals(1, countJan);
    }

    @Test
    void testFindTopExpenses() {
        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(sampleTransactions);

        Assertions.assertEquals(4, topExpenses.size());
        Assertions.assertEquals(-3200.0, topExpenses.get(0).getAmount(), "Перша витрата має бути найбільшою за абсолютним значенням");
        Assertions.assertEquals("Оренда квартири", topExpenses.get(0).getDescription());
    }
}