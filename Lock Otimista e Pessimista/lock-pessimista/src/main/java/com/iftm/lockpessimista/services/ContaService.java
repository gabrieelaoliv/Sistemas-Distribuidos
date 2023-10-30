package com.iftm.lockpessimista.services;

import com.iftm.lockpessimista.models.Conta;
import com.iftm.lockpessimista.repositories.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    public ResponseEntity<Conta> save (Conta conta) {
        return ResponseEntity.ok(contaRepository.save(conta));
    }

    @Transactional
    public ResponseEntity<?> sacar(String conta, Double valor) {
        var contaDb = contaRepository.findByConta(conta).orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Conta inexistente"));

        if (!contaDb.sacar(valor))
            return ResponseEntity.status(400).body("Saldo insuficiente");

        return ResponseEntity.ok(contaRepository.save(contaDb));

    }

    @Transactional
    public ResponseEntity<?> depositar(String conta, Double valor) {
        var contaDb = contaRepository.findByConta(conta).orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Conta inexistente"));

        if (!contaDb.depositar(valor))
            return ResponseEntity.status(400).body("Valor incorreto");

        return ResponseEntity.ok(contaRepository.save(contaDb));

    }
}
