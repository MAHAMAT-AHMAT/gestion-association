package ma.casascolarisation.services;

import ma.casascolarisation.entities.Eleve;

import java.util.List;

public interface EleveService {
    List<Eleve> findAll();
    Eleve findById(Long id);
    Eleve save(Eleve obj);
    void delete(Long id);
}
