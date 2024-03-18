package es.babel.bankExercise.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class BankAccount {
    @Id
    private String id;

    @OneToMany(mappedBy = "bankAccount")
    private List<Operation> operations;

    private double totalAmount;

    @Column(name = "owner_id")
    private int ownerId;
    private String entity;// Cambiado a int para almacenar solo el ID del cliente
}
