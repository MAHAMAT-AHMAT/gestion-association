package ma.casascolarisation.repositories;

import ma.casascolarisation.entities.Depense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepenseRepo extends JpaRepository<Depense, Long> {

}
