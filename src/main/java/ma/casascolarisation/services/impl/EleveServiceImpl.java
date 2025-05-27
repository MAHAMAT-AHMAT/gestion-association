package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.AnneeScolaire;
import ma.casascolarisation.entities.Eleve;
import ma.casascolarisation.entities.Fournisseur;
import ma.casascolarisation.entities.NiveauScolaire;
import ma.casascolarisation.repositories.AnneeScolaireRepo;
import ma.casascolarisation.repositories.EleveRepo;
import ma.casascolarisation.repositories.FournisseurRepo;
import ma.casascolarisation.repositories.NiveauScolaireRepo;
import ma.casascolarisation.services.EleveService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EleveServiceImpl implements EleveService {

    private final EleveRepo eleveRepo;
    private final FournisseurRepo fournisseurRepo;
    private final NiveauScolaireRepo niveauScolaireRepo;
    private final AnneeScolaireRepo anneeScolaireRepo;

    @Override
    public List<Eleve> findAll() {
        return eleveRepo.findAll();
    }

    @Override
    public Eleve findById(Long id) {
        return eleveRepo.findById(id).orElse(null);
    }

    @Override
    public Eleve save(Eleve obj) {
        // Reload fournisseur from database if it exists
        if (obj.getFournisseur() != null && obj.getFournisseur().getId() != null) {
            Fournisseur fournisseur = fournisseurRepo.findById(obj.getFournisseur().getId()).orElse(null);
            if (fournisseur != null) {
                obj.setFournisseur(fournisseur);
            }
        }

        // Reload niveau from database if it exists
        if (obj.getNiveau() != null && obj.getNiveau().getId() != null) {
            NiveauScolaire niveau = niveauScolaireRepo.findById(obj.getNiveau().getId()).orElse(null);
            if (niveau != null) {
                obj.setNiveau(niveau);
            }
        }

        // Reload annee from database if it exists
        if (obj.getAnnee() != null && obj.getAnnee().getId() != null) {
            AnneeScolaire annee = anneeScolaireRepo.findById(obj.getAnnee().getId()).orElse(null);
            if (annee != null) {
                obj.setAnnee(annee);
            }
        }

        return eleveRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        eleveRepo.deleteById(id);
    }
}
