package ma.casascolarisation.repositories;

import ma.casascolarisation.entities.Parent;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParentRepo extends JpaRepository<Parent, Long> {
    @EntityGraph(attributePaths = {"eleve"})
    @Override
    List<Parent> findAll();

    @EntityGraph(attributePaths = {"eleve"})
    @Override
    Optional<Parent> findById(Long id);
}
