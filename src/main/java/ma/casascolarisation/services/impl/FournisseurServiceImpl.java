package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Fournisseur;
import ma.casascolarisation.repositories.FournisseurRepo;
import ma.casascolarisation.services.FournisseurService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FournisseurServiceImpl implements FournisseurService {

    private final FournisseurRepo fournisseurRepo;

    @Override
    public List<Fournisseur> findAll() {
        return fournisseurRepo.findAll();
    }

    @Override
    public Fournisseur findById(Long id) {
        return fournisseurRepo.findById(id).orElse(null);
    }

    @Override
    public Fournisseur save(Fournisseur obj) {
        return fournisseurRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        fournisseurRepo.deleteById(id);
    }
}