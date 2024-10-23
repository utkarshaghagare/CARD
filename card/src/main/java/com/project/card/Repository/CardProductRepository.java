package com.project.card.Repository;

import com.project.card.Entity.CardProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardProductRepository extends JpaRepository<CardProduct, Long> {
}