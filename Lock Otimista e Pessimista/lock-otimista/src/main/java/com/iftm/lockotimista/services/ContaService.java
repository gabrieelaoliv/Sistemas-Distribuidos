package com.iftm.lockotimista.services;

import com.iftm.lockotimista.models.Conta;
import com.iftm.lockotimista.repositories.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    public ResponseEntity<Conta> save (Conta conta) {
        return ResponseEntity.ok(contaRepository.save(conta));
    }

    @Transactional
    public ResponseEntity<?> sacar(String numConta, Double valor) {
        var conta = contaRepository.findByConta(numConta).orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Conta inexistente"));

        if (!conta.sacar(valor))
            return ResponseEntity.status(400).body("Saldo insuficiente");

        return ResponseEntity.ok(contaRepository.save(conta));
    }

    @Transactional
    public ResponseEntity<?> deposito(String numConta, Double valor) {
        var conta = contaRepository.findByConta(numConta).orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Conta inexistente"));

        if (!conta.depositar(valor))
            return ResponseEntity.status(400).body("Valor incorreto");


        return ResponseEntity.ok(contaRepository.save(conta));
    }
}
