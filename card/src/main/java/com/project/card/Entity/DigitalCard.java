package com.project.card.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "digital_card")
public class DigitalCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long digitalCardId;

    public String digitalCardPhoto;

    // Constructor without getters/setters
    public DigitalCard(Long digitalCardId, String digitalCardPhoto) {
        this.digitalCardId = digitalCardId;
        this.digitalCardPhoto = digitalCardPhoto;
    }

    // Default constructor required by JPA
    public DigitalCard() {
    }

    // Override toString method for debugging if needed
    @Override
    public String toString() {
        return "DigitalCard{" +
               "digitalCardId=" + digitalCardId +
               ", digitalCardPhoto='" + digitalCardPhoto + '\'' +
               '}';
    }
}
