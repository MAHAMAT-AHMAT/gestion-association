package ma.casascolarisation.repositories;

import ma.casascolarisation.entities.NiveauScolaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NiveauScolaireRepo extends JpaRepository<NiveauScolaire, Long> {

}
