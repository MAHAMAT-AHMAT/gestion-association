package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Fournisseur;
import ma.casascolarisation.repositories.FournisseurRepo;
import ma.casascolarisation.services.FournisseurService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FournisseurServiceImpl implements FournisseurService {

    private final FournisseurRepo fournisseurRepo;

    @Override
    public List<Fournisseur> findAll() {
        return fournisseurRepo.findAll();
    }

    @Override
    public Fournisseur findById(Long id) {
        return fournisseurRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fournisseur introuvable pour l'id " + id));
    }

    @Override
    public Fournisseur save(Fournisseur fournisseur) {
        if (fournisseur == null) {
            throw new IllegalArgumentException("Le fournisseur ne peut pas être null");
        }
        if (fournisseur.getTypeFournisseur() == null) {
            throw new IllegalArgumentException("Le type de fournisseur est obligatoire");
        }
        return fournisseurRepo.save(fournisseur);
    }

    @Override
    public void delete(Long id) {
        if (!fournisseurRepo.existsById(id)) {
            throw new RuntimeException("Fournisseur introuvable avec id = " + id);
        }
        fournisseurRepo.deleteById(id);
    }
}