package com.project.card.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "card_contact")
public class CardContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long contactId;

    public String phoneNumber;

    public String email;

    public String whatsappNumber;

    // Constructor without getters/setters
    public CardContact(Long id, String phoneNumber, String email, String whatsappNumber) {
        this.contactId = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.whatsappNumber = whatsappNumber;
    }

    // Default constructor required by JPA
    public CardContact() {
    }

    // Override toString method for debugging if needed
    @Override
    public String toString() {
        return "CardContact{" +
               "id=" + contactId +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", email='" + email + '\'' +
               ", whatsapp='" + whatsappNumber + '\'' +
               '}';
    }
}
