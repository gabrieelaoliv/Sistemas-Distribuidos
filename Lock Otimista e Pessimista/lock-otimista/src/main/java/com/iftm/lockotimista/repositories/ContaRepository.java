package com.iftm.lockotimista.repositories;

import com.iftm.lockotimista.models.Conta;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    Optional<Conta> findByConta (String conta);
}
