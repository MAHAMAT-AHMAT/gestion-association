package ma.casascolarisation.repositories;

import ma.casascolarisation.entities.Donateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonateurRepo extends JpaRepository<Donateur, Long> {

}
