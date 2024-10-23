package com.project.card.Repository;

import com.project.card.Entity.DigitalCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DigitalCardRepository extends JpaRepository<DigitalCard, Long> {
}