package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Facture;
import ma.casascolarisation.repositories.FactureRepo;
import ma.casascolarisation.services.FactureService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FactureServiceImpl implements FactureService {

    private final FactureRepo factureRepo;

    @Override
    public List<Facture> findAll() {
        return factureRepo.findAll();
    }

    @Override
    public Facture findById(Long id) {
        return factureRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Facture introuvable pour l'id " + id));
    }

    @Override
    public Facture save(Facture obj) {
        return factureRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        factureRepo.deleteById(id);
    }
}