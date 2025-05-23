package ma.casascolarisation.repositories;

import ma.casascolarisation.entities.TypeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeFournisseurRepo extends JpaRepository<TypeFournisseur, Long> {

}
