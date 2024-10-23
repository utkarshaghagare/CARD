package com.project.card.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "nfc_card")
@Setter
@Getter
public class NfcCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long nfcCardId;

    public String nfcCardPhoto;
    
    public Double nfcCardAmount;

    // Constructor without getters/setters
    public NfcCard(Long nfcCardId, String nfcCardPhoto, Double nfcCardAmount) {
        this.nfcCardId = nfcCardId;
        this.nfcCardPhoto = nfcCardPhoto;
        this.nfcCardAmount = nfcCardAmount;
    }

    // Default constructor required by JPA
    public NfcCard() {
    }

    // Override toString method for debugging if needed
    @Override
    public String toString() {
        return "NfcCard{" +
               "nfcCardId=" + nfcCardId +
               ", nfcCardPhoto='" + nfcCardPhoto + '\'' +
               ", nfcCardAmount=" + nfcCardAmount +
               '}';
    }
}
