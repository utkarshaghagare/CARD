package com.project.card.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "card_services")
@Getter
@Setter
public class CardServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long serviceId;

    public String serviceName;


    // Default constructor required by JPA
    public CardServices() {
    }

}
