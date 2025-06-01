package ma.casascolarisation.repositories;

import ma.casascolarisation.entities.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EleveRepo extends JpaRepository<Eleve, Long> {

}
