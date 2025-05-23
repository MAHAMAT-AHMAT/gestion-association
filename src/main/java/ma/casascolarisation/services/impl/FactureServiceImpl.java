package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Facture;
import ma.casascolarisation.repositories.FactureRepo;
import ma.casascolarisation.services.FactureService;
import org.springframework.stereotype.Service;

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
        return factureRepo.findById(id).orElse(null);
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