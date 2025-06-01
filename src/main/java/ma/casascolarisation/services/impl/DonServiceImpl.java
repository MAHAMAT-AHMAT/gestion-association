package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Don;
import ma.casascolarisation.repositories.DonRepo;
import ma.casascolarisation.services.DonService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonServiceImpl implements DonService {

    private final DonRepo donRepo;

    @Override
    public List<Don> findAll() {
        return donRepo.findAll();
    }

    @Override
    public Don findById(Long id) {
        return donRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Don introuvable pour l'id " + id));
    }

    @Override
    public Don save(Don obj) {
        return donRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        donRepo.deleteById(id);
    }
}