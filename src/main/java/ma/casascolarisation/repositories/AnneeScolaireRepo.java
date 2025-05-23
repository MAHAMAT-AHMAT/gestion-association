package ma.casascolarisation.repositories;

import ma.casascolarisation.entities.AnneeScolaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnneeScolaireRepo extends JpaRepository<AnneeScolaire, Long> {

}
