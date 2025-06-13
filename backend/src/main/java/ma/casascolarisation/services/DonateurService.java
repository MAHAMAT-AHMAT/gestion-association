package ma.casascolarisation.services;

import ma.casascolarisation.entities.Donateur;

import java.util.List;

public interface DonateurService {
    List<Donateur> findAll();
    Donateur findById(Long id);
    Donateur save(Donateur obj);
    void delete(Long id);
}
