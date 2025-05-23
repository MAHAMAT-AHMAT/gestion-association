package ma.casascolarisation.services;

import ma.casascolarisation.entities.TypeFournisseur;

import java.util.List;

public interface TypeFournisseurService {
    List<TypeFournisseur> findAll();
    TypeFournisseur findById(Long id);
    TypeFournisseur save(TypeFournisseur obj);
    void delete(Long id);
}
