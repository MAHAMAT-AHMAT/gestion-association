package ma.casascolarisation.services;

import ma.casascolarisation.entities.AnneeScolaire;

import java.util.List;

public interface AnneeScolaireService {
    List<AnneeScolaire> findAll();
    AnneeScolaire findById(Long id);
    AnneeScolaire save(AnneeScolaire obj);
    void delete(Long id);
}
