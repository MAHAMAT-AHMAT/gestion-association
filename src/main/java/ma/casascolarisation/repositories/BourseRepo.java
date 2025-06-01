package ma.casascolarisation.repositories;

import ma.casascolarisation.entities.Bourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BourseRepo extends JpaRepository<Bourse, Long> {

}
