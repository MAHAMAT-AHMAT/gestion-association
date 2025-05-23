package ma.casascolarisation.repositories;

import ma.casascolarisation.entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurRepo extends JpaRepository<Fournisseur, Long> {

}
