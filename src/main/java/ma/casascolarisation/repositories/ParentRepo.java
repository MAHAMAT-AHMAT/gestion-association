package ma.casascolarisation.repositories;

import ma.casascolarisation.entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepo extends JpaRepository<Parent, Long> {

}
