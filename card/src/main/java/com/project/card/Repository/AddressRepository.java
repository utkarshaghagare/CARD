package com.project.card.Repository;

import com.project.card.Entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<UserAddress, Long> {
}