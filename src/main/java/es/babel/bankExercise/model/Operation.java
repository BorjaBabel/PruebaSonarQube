package es.babel.bankExercise.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String bankAccount;
    private String fecha;
    private Double cantidad;
    private String descripcion;
    private double interest;
    private String Entity;
}
