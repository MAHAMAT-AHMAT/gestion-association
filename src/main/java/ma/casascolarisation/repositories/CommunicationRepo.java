package ma.casascolarisation.repositories;

import ma.casascolarisation.entities.Communication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationRepo extends JpaRepository<Communication, Long> {

}
