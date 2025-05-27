package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Communication;
import ma.casascolarisation.entities.Donateur;
import ma.casascolarisation.entities.Utilisateur;
import ma.casascolarisation.repositories.CommunicationRepo;
import ma.casascolarisation.repositories.DonateurRepo;
import ma.casascolarisation.repositories.UtilisateurRepo;
import ma.casascolarisation.services.CommunicationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunicationServiceImpl implements CommunicationService {

    private final CommunicationRepo communicationRepo;
    private final DonateurRepo donateurRepo;
    private final UtilisateurRepo utilisateurRepo;

    @Override
    public List<Communication> findAll() {
        return communicationRepo.findAll();
    }

    @Override
    public Communication findById(Long id) {
        return communicationRepo.findById(id).orElse(null);
    }

    @Override
    public Communication save(Communication obj) {
        // Reload donateur from database if it exists
        if (obj.getDonateur() != null && obj.getDonateur().getId() != null) {
            Donateur donateur = donateurRepo.findById(obj.getDonateur().getId()).orElse(null);
            if (donateur != null) {
                obj.setDonateur(donateur);
            }
        }

        // Reload utilisateur from database if it exists
        if (obj.getUtilisateur() != null && obj.getUtilisateur().getId() != null) {
            Utilisateur utilisateur = utilisateurRepo.findById(obj.getUtilisateur().getId()).orElse(null);
            if (utilisateur != null) {
                obj.setUtilisateur(utilisateur);
            }
        }

        return communicationRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        communicationRepo.deleteById(id);
    }
}
