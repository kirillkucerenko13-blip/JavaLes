package com.example.order.processing;

import com.example.order.storage.Product;
import java.util.logging.Logger;

public class OrderProcessor<T extends Product> {
    private static final Logger LOGGER = Logger.getLogger(OrderProcessor.class.getName());
    private final T product;

    public OrderProcessor(T product) {
        if (product == null) {
            throw new IllegalArgumentException("Товар не може бути null");
        }
        this.product = product;
    }

    public void processOrder() throws OrderProcessingException {
        try {
            if (product.getPrice() <= 0) {
                throw new OrderProcessingException("Некоректна ціна товару: " + product.getName());
            }


            Thread.sleep(50);
            LOGGER.info(() -> "Замовлення успішно оброблено: " + product.getName() + " (Ціна: " + product.getPrice() + ")");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new OrderProcessingException("Потік обробки було перервано для товару: " + product.getName(), e);
        }
    }

    public T getProduct() {
        return product;
    }
}