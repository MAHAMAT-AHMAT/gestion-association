package ma.casascolarisation.repositories;

import ma.casascolarisation.entities.Bourse;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BourseRepo extends JpaRepository<Bourse, Long> {
    @EntityGraph(attributePaths = {"eleve", "don.donateur", "annee"})
    @Override
    List<Bourse> findAll();

    @EntityGraph(attributePaths = {"eleve", "don.donateur", "annee"})
    @Override
    Optional<Bourse> findById(Long id);
}