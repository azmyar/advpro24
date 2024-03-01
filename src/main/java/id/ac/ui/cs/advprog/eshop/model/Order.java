package id.ac.ui.cs.advprog.eshop.model;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Builder
@Getter
public class Order {
    private String Id;
    private List<Product> products;
    private Long orderTime;
    private String author;
    private String status;

    public Order(String orderId, List<Product> products, Long orderTime, String author) {
        this.Id = orderId;
        this.orderTime = orderTime;
        this.author = author;
        this.status = "WAITING_PAYMENT";

        if (products.isEmpty()) {
            throw new IllegalArgumentException("Your products is empty");
        } else {
            this.products = products;
        }
    }

    public Order(String orderId, List<Product> products, Long orderTime, String author, String status) {
        this.Id = orderId;
        this.products = products;
        this.orderTime = orderTime;
        this.author = author;

        String[] statusList = {"WAITING_PAYMENT", "FAILED", "CANCELLED", "SUCCESS"};
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))) {
            throw new IllegalArgumentException("Invalid status: " + status);
        } else {
            this.status =  status;
        }

    }

    public void setStatus(String status) {
        String[] statusList = {"WAITING_PAYMENT", "FAILED", "CANCELLED", "SUCCESS"};
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))) {
            throw new IllegalArgumentException("Invalid status: " + status);
        } else {
            this.status =  status;
        }
    }
}