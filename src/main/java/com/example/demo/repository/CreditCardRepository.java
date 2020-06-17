package com.example.demo.repository;

import com.example.demo.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
  Optional<CreditCard> findByNumber(Long number);
}
