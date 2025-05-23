package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Eleve;
import ma.casascolarisation.repositories.EleveRepo;
import ma.casascolarisation.services.EleveService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EleveServiceImpl implements EleveService {

    private final EleveRepo eleveRepo;

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
        return eleveRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        eleveRepo.deleteById(id);
    }
}