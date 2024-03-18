package es.babel.bankExercise.repository;

import es.babel.bankExercise.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
