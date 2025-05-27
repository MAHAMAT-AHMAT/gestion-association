package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.AnneeScolaire;
import ma.casascolarisation.entities.Don;
import ma.casascolarisation.entities.Donateur;
import ma.casascolarisation.repositories.AnneeScolaireRepo;
import ma.casascolarisation.repositories.DonRepo;
import ma.casascolarisation.repositories.DonateurRepo;
import ma.casascolarisation.services.DonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonServiceImpl implements DonService {

    private final DonRepo donRepo;
    private final DonateurRepo donateurRepo;
    private final AnneeScolaireRepo anneeScolaireRepo;

    @Override
    public List<Don> findAll() {
        return donRepo.findAll();
    }

    @Override
    public Don findById(Long id) {
        return donRepo.findById(id).orElse(null);
    }

    @Override
    public Don save(Don obj) {
        // Reload donateur from database if it exists
        if (obj.getDonateur() != null && obj.getDonateur().getId() != null) {
            Donateur donateur = donateurRepo.findById(obj.getDonateur().getId()).orElse(null);
            if (donateur != null) {
                obj.setDonateur(donateur);
            }
        }

        // Reload annee from database if it exists
        if (obj.getAnnee() != null && obj.getAnnee().getId() != null) {
            AnneeScolaire annee = anneeScolaireRepo.findById(obj.getAnnee().getId()).orElse(null);
            if (annee != null) {
                obj.setAnnee(annee);
            }
        }

        return donRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        donRepo.deleteById(id);
    }
}
