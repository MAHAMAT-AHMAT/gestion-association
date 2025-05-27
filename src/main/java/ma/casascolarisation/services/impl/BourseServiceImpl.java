package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.AnneeScolaire;
import ma.casascolarisation.entities.Bourse;
import ma.casascolarisation.entities.Don;
import ma.casascolarisation.entities.Eleve;
import ma.casascolarisation.repositories.AnneeScolaireRepo;
import ma.casascolarisation.repositories.BourseRepo;
import ma.casascolarisation.repositories.DonRepo;
import ma.casascolarisation.repositories.EleveRepo;
import ma.casascolarisation.services.BourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BourseServiceImpl implements BourseService {

    private final BourseRepo bourseRepo;
    private final EleveRepo eleveRepo;
    private final DonRepo donRepo;
    private final AnneeScolaireRepo anneeScolaireRepo;

    @Override
    public List<Bourse> findAll() {
        return bourseRepo.findAll();
    }

    @Override
    public Bourse findById(Long id) {
        return bourseRepo.findById(id).orElse(null);
    }

    @Override
    public Bourse save(Bourse obj) {
        // Reload eleve from database if it exists
        if (obj.getEleve() != null && obj.getEleve().getId() != null) {
            Eleve eleve = eleveRepo.findById(obj.getEleve().getId()).orElse(null);
            if (eleve != null) {
                obj.setEleve(eleve);
            }
        }

        // Reload don from database if it exists
        if (obj.getDon() != null && obj.getDon().getId() != null) {
            Don don = donRepo.findById(obj.getDon().getId()).orElse(null);
            if (don != null) {
                obj.setDon(don);
            }
        }

        // Reload annee from database if it exists
        if (obj.getAnnee() != null && obj.getAnnee().getId() != null) {
            AnneeScolaire annee = anneeScolaireRepo.findById(obj.getAnnee().getId()).orElse(null);
            if (annee != null) {
                obj.setAnnee(annee);
            }
        }

        return bourseRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        bourseRepo.deleteById(id);
    }
}
