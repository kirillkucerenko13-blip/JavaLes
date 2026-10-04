package org.example;

import org.example.model.Transaction;
import org.example.reader.TransactionCSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TransactionCSVReaderTest {

    @Test
    void testReadTransactionsFromUrl() {
       String filePath = "file:///" + System.getProperty("user.dir").replace("\\", "/") + "/pr2.csv";
        // String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        Assertions.assertNotNull(transactions, "Список транзакцій не повинен бути null");
        Assertions.assertFalse(transactions.isEmpty(), "Список транзакцій не повинен бути порожнім");
    }
}