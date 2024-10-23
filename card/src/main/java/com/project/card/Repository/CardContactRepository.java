package com.project.card.Repository;

import com.project.card.Entity.CardContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardContactRepository extends JpaRepository<CardContact, Long> {
}