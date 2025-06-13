package ma.casascolarisation.services;

import ma.casascolarisation.entities.Bourse;

import java.util.List;

public interface BourseService {
    List<Bourse> findAll();
    Bourse findById(Long id);
    Bourse save(Bourse obj);
    void delete(Long id);
}
