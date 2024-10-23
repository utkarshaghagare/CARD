package com.project.card.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "orders") // 'order' is a reserved keyword, so we use 'orders'
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_nfc", referencedColumnName = "user_nfc_id", nullable = false)
    private UserNfcDetails userNfcDetails;  // Foreign key to UserNsr entity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_address_id", referencedColumnName = "address_id", nullable = false)
    private UserAddress deliveryAddress;  // Foreign key to UserAddress entity

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "alternate_phone_number")
    private String alternatePhoneNumber;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;  // E.g., "Pending", "Shipped", "Delivered"

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public Order() {
    }

    public Order(UserNfcDetails userNsr, UserAddress deliveryAddress, String phoneNumber, String alternatePhoneNumber, Double totalAmount, String orderStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userNfcDetails = userNsr;
        this.deliveryAddress = deliveryAddress;
        this.phoneNumber = phoneNumber;
        this.alternatePhoneNumber = alternatePhoneNumber;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and toString

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userNsr=" + userNfcDetails +
                ", deliveryAddress=" + deliveryAddress +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", alternatePhoneNumber='" + alternatePhoneNumber + '\'' +
                ", totalAmount=" + totalAmount +
                ", orderStatus='" + orderStatus + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
