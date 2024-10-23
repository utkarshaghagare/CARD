package com.project.card.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "card_product")
@Getter
@Setter
public class CardProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long productId;

    public String category;
    public String productName;
    public String productImage;

    // Constructor without getters/setters
    public CardProduct(Long productId, String category, String productName, String productImage) {
        this.productId = productId;
        this.category = category;
        this.productName = productName;
        this.productImage = productImage;
    }

    // Default constructor required by JPA
    public CardProduct() {
    }

    // Override toString method for debugging if needed
    @Override
    public String toString() {
        return "CardProduct{" +
                "productId=" + productId +
                ", category='" + category + '\'' +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                '}';
    }
}
