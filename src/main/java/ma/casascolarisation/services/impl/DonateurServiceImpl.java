package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Donateur;
import ma.casascolarisation.repositories.DonateurRepo;
import ma.casascolarisation.services.DonateurService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonateurServiceImpl implements DonateurService {

    private final DonateurRepo donateurRepo;

    @Override
    public List<Donateur> findAll() {
        return donateurRepo.findAll();
    }

    @Override
    public Donateur findById(Long id) {
        return donateurRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Donateur introuvable pour l'id " + id));
    }

    @Override
    public Donateur save(Donateur obj) {
        return donateurRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        donateurRepo.deleteById(id);
    }
}