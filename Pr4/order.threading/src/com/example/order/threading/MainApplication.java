package com.example.order.threading;

import com.example.order.processing.OrderProcessingException;
import com.example.order.processing.OrderProcessor;
import com.example.order.storage.Clothing;
import com.example.order.storage.Electronics;
import com.example.order.storage.Product;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MainApplication {
    public static void main(String[] args) {
        Faker faker = new Faker();
        List<Product> catalog = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            catalog.add(Electronics.builder()
                    .name(faker.commerce().productName())
                    .price(Double.parseDouble(faker.commerce().price(150, 1500)))
                    .brand(faker.device().modelName())
                    .warrantyMonths(24)
                    .build());

            catalog.add(Clothing.builder()
                    .name(faker.commerce().productName())
                    .price(Double.parseDouble(faker.commerce().price(20, 150)))
                    .size(faker.options().option("S", "M", "L", "XL"))
                    .material(faker.commerce().material())
                    .build());
        }


        List<OrderProcessor<? extends Product>> processors = catalog.stream()
                .filter(product -> product.getPrice() > 40) // Лямбда для фільтрації
                .map(OrderProcessor::new)                 // Посилання на конструктор (Method Reference)
                .collect(Collectors.toList());


        ExecutorService executorService = Executors.newFixedThreadPool(3);

        System.out.println("=== ПОЧАТОК БАГАТОПОТОКОВОЇ ОБРОБКИ ЗАМОВЛЕНЬ ===");

        for (OrderProcessor<? extends Product> processor : processors) {
            executorService.submit(() -> {
                try {
                    processor.processOrder();
                    System.out.println("[Потік: " + Thread.currentThread().getName() +
                            "] Оброблено замовлення на: " + processor.getProduct().getName());
                } catch (OrderProcessingException e) {
                    System.err.println("[Помилка потоку] " + e.getMessage());
                }
            });
        }

        executorService.shutdown();
    }
}