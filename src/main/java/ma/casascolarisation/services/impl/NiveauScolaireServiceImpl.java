package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.NiveauScolaire;
import ma.casascolarisation.repositories.NiveauScolaireRepo;
import ma.casascolarisation.services.NiveauScolaireService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NiveauScolaireServiceImpl implements NiveauScolaireService {

    private final NiveauScolaireRepo niveauScolaireRepo;

    @Override
    public List<NiveauScolaire> findAll() {
        return niveauScolaireRepo.findAll();
    }

    @Override
    public NiveauScolaire findById(Long id) {
        return niveauScolaireRepo.findById(id).orElse(null);
    }

    @Override
    public NiveauScolaire save(NiveauScolaire obj) {
        return niveauScolaireRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        niveauScolaireRepo.deleteById(id);
    }
}