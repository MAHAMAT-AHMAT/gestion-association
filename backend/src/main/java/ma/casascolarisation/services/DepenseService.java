package ma.casascolarisation.services;

import ma.casascolarisation.entities.Depense;

import java.util.List;

public interface DepenseService {
    List<Depense> findAll();
    Depense findById(Long id);
    Depense save(Depense obj);
    void delete(Long id);
}
