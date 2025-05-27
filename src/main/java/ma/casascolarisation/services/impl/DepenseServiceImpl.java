package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.AnneeScolaire;
import ma.casascolarisation.entities.Depense;
import ma.casascolarisation.entities.Eleve;
import ma.casascolarisation.entities.Fournisseur;
import ma.casascolarisation.repositories.AnneeScolaireRepo;
import ma.casascolarisation.repositories.DepenseRepo;
import ma.casascolarisation.repositories.EleveRepo;
import ma.casascolarisation.repositories.FournisseurRepo;
import ma.casascolarisation.services.DepenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepenseServiceImpl implements DepenseService {

    private final DepenseRepo depenseRepo;
    private final FournisseurRepo fournisseurRepo;
    private final EleveRepo eleveRepo;
    private final AnneeScolaireRepo anneeScolaireRepo;

    @Override
    public List<Depense> findAll() {
        return depenseRepo.findAll();
    }

    @Override
    public Depense findById(Long id) {
        return depenseRepo.findById(id).orElse(null);
    }

    @Override
    public Depense save(Depense obj) {
        // Reload fournisseur from database if it exists
        if (obj.getFournisseur() != null && obj.getFournisseur().getId() != null) {
            Fournisseur fournisseur = fournisseurRepo.findById(obj.getFournisseur().getId()).orElse(null);
            if (fournisseur != null) {
                obj.setFournisseur(fournisseur);
            }
        }

        // Reload eleve from database if it exists
        if (obj.getEleve() != null && obj.getEleve().getId() != null) {
            Eleve eleve = eleveRepo.findById(obj.getEleve().getId()).orElse(null);
            if (eleve != null) {
                obj.setEleve(eleve);
            }
        }

        // Reload annee from database if it exists
        if (obj.getAnnee() != null && obj.getAnnee().getId() != null) {
            AnneeScolaire annee = anneeScolaireRepo.findById(obj.getAnnee().getId()).orElse(null);
            if (annee != null) {
                obj.setAnnee(annee);
            }
        }

        return depenseRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        depenseRepo.deleteById(id);
    }
}
