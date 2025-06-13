package ma.casascolarisation.services;

import ma.casascolarisation.entities.NiveauScolaire;

import java.util.List;

public interface NiveauScolaireService {
    List<NiveauScolaire> findAll();
    NiveauScolaire findById(Long id);
    NiveauScolaire save(NiveauScolaire obj);
    void delete(Long id);
}
