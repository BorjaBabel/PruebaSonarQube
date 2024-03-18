package es.babel.bankExercise.model;

import lombok.Data;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("deposit")
@Data
public class OperationTransfer extends Operation{
    private String destinationAccount;
    private String destinationEntity;
}
