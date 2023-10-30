package com.iftm.lockpessimista.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.annotation.Documented;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nome = "nomeCompleto")
    private String nomeCompleto;
    private String conta;
    private String agencia;
    private Double saldo;
    @Version
    private int version;


    public boolean sacar( Double valor) {
        if (valor > this.saldo) {
            return false;
        } else {
            this.saldo -= valor;
            return true;
        }
    }

    public boolean depositar( Double valor ) {

        if (valor <= 0) {
            return false;
        } else {
            this.saldo += valor;
            return true;
        }
    }
}
