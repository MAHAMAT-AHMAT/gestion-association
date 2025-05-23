package ma.casascolarisation.services;

import ma.casascolarisation.entities.Facture;

import java.util.List;

public interface FactureService {
    List<Facture> findAll();
    Facture findById(Long id);
    Facture save(Facture obj);
    void delete(Long id);
}
