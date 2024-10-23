package com.project.card.Service;

import com.project.card.Entity.Order;
import com.project.card.Entity.UserAddress;
import com.project.card.Entity.UserNfcDetails;
import com.project.card.Model.OrderRequestDTO;
import com.project.card.Repository.AddressRepository;
import com.project.card.Repository.OrderRepository;
import com.project.card.Repository.UserNfcDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserNfcDetailsRepository userNfcDetailsRepository;

    @Autowired
    private AddressRepository userAddressRepository;

    public Order createOrder(OrderRequestDTO orderRequestDTO) {

        UserNfcDetails userNfcDetails = userNfcDetailsRepository.findById(orderRequestDTO.getUserNfcId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserNfcDetails ID"));

        UserAddress userAddress = userAddressRepository.findById(orderRequestDTO.getDeliveryAddressId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Delivery Address ID"));

        // Determine the order status based on the total amount
        String orderStatus = "Pending";

        // Build the Order entity
        Order order = new Order(
                userNfcDetails,
                userAddress,
                orderRequestDTO.getPhoneNumber(),
                orderRequestDTO.getAlternatePhoneNumber(),
                orderRequestDTO.getTotalAmount(),
                orderStatus,  // Set the determined order status
                LocalDateTime.now(),
                null  // Initially, updatedAt is null
        );

        // Save the order to the database
        return orderRepository.save(order);
    }
}
