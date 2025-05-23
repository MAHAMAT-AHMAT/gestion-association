package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Utilisateur;
import ma.casascolarisation.repositories.UtilisateurRepo;
import ma.casascolarisation.services.UtilisateurService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepo utilisateurRepo;

    @Override
    public List<Utilisateur> findAll() {
        return utilisateurRepo.findAll();
    }

    @Override
    public Utilisateur findById(Long id) {
        return utilisateurRepo.findById(id).orElse(null);
    }

    @Override
    public Utilisateur save(Utilisateur obj) {
        return utilisateurRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        utilisateurRepo.deleteById(id);
    }
}