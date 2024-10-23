package com.project.card.Repository;

import com.project.card.Entity.CardServices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardServicesRepository extends JpaRepository<CardServices, Long> {
}