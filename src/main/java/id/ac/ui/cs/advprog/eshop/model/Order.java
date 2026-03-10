package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class Order {
    private String id;
    private List<Product> products;
    private Long orderTime;
    private String author;
    private String status;

    // Status default "WAITING_PAYMENT"
    public Order(String id, List<Product> products, Long orderTime, String author) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Product tidak boleh kosong");
        }
        this.id = id;
        this.products = products;
        this.orderTime = orderTime;
        this.author = author;
        this.status = "WAITING_PAYMENT";
    }

    // Dengan parameter status
    public Order(String id, List<Product> products, Long orderTime, String author, String status) {
        this(id, products, orderTime, author);

        this.setStatus(status);
    }

    public void setStatus(String status) {
        List<String> validStatus = Arrays.asList("WAITING_PAYMENT", "FAILED", "CANCELLED", "SUCCESS");

        if (validStatus.contains(status)) {
            this.status = status;
        } else {
            // Status invalid
            throw new IllegalArgumentException("Status tidak valid: " + status);
        }
    }
}