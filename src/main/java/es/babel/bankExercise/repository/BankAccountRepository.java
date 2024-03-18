package es.babel.bankExercise.repository;
import es.babel.bankExercise.model.BankAccount;
import es.babel.bankExercise.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    BankAccount findById(String id);
}
