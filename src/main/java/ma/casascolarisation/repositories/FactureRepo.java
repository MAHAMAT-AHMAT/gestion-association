package ma.casascolarisation.repositories;

import ma.casascolarisation.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepo extends JpaRepository<Facture, Long> {

}
