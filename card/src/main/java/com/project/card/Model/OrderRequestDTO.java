package com.project.card.Model;

import java.time.LocalDateTime;

public class OrderRequestDTO {

    private String userNfcId;  // We'll take this ID to associate with UserNfcDetails
    private Long deliveryAddressId;  // To associate with UserAddress
    private String phoneNumber;
    private String alternatePhoneNumber;
    private Double totalAmount;

    // Getters and setters

    public String getUserNfcId() {
        return userNfcId;
    }

    public void setUserNfcId(String userNfcId) {
        this.userNfcId = userNfcId;
    }

    public Long getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(Long deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAlternatePhoneNumber() {
        return alternatePhoneNumber;
    }

    public void setAlternatePhoneNumber(String alternatePhoneNumber) {
        this.alternatePhoneNumber = alternatePhoneNumber;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
