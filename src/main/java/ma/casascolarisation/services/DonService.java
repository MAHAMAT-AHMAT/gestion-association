package ma.casascolarisation.services;

import ma.casascolarisation.entities.Don;

import java.util.List;

public interface DonService {
    List<Don> findAll();
    Don findById(Long id);
    Don save(Don obj);
    void delete(Long id);
}
