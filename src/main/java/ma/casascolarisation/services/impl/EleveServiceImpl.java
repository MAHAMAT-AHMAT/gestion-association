package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Eleve;
import ma.casascolarisation.repositories.EleveRepo;
import ma.casascolarisation.services.EleveService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return eleveRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Eleve introuvable pour l'id " + id));
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