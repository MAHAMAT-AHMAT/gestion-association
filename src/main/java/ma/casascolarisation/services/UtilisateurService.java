package ma.casascolarisation.services;

import ma.casascolarisation.entities.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    List<Utilisateur> findAll();
    Utilisateur findById(Long id);
    Utilisateur save(Utilisateur obj);
    void delete(Long id);
}
