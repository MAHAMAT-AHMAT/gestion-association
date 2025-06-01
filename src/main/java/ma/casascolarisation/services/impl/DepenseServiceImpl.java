package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Depense;
import ma.casascolarisation.repositories.DepenseRepo;
import ma.casascolarisation.services.DepenseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepenseServiceImpl implements DepenseService {

    private final DepenseRepo depenseRepo;

    @Override
    public List<Depense> findAll() {
        return depenseRepo.findAll();
    }

    @Override
    public Depense findById(Long id) {
        return depenseRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Depense introuvable pour l'id " + id));
    }

    @Override
    public Depense save(Depense obj) {
        return depenseRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        depenseRepo.deleteById(id);
    }
}