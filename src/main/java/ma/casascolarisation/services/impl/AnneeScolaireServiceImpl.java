package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.AnneeScolaire;
import ma.casascolarisation.repositories.AnneeScolaireRepo;
import ma.casascolarisation.services.AnneeScolaireService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnneeScolaireServiceImpl implements AnneeScolaireService {

    private final AnneeScolaireRepo anneeScolaireRepo;

    @Override
    public List<AnneeScolaire> findAll() {
        return anneeScolaireRepo.findAll();
    }

    @Override
    public AnneeScolaire findById(Long id) {
        return anneeScolaireRepo.findById(id).orElse(null);
    }

    @Override
    public AnneeScolaire save(AnneeScolaire obj) {
        return anneeScolaireRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        anneeScolaireRepo.deleteById(id);
    }
}