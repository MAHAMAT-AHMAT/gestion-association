package ma.casascolarisation.services;

import ma.casascolarisation.entities.Fournisseur;

import java.util.List;

public interface FournisseurService {
    List<Fournisseur> findAll();
    Fournisseur findById(Long id);
    Fournisseur save(Fournisseur obj);
    void delete(Long id);
}
