package es.babel.bankExercise.repository;
import es.babel.bankExercise.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Client, Integer> {
    Client findById(long id);

}
