package com.project.card.Repository;

import com.project.card.Entity.NfcCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NfcCardRepository extends JpaRepository<NfcCard, Long> {
}