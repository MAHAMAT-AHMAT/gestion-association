package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.TypeFournisseur;
import ma.casascolarisation.repositories.TypeFournisseurRepo;
import ma.casascolarisation.services.TypeFournisseurService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeFournisseurServiceImpl implements TypeFournisseurService {

    private final TypeFournisseurRepo typeFournisseurRepo;

    @Override
    public List<TypeFournisseur> findAll() {
        return typeFournisseurRepo.findAll();
    }

    @Override
    public TypeFournisseur findById(Long id) {
        return typeFournisseurRepo.findById(id).orElse(null);
    }

    @Override
    public TypeFournisseur save(TypeFournisseur obj) {
        return typeFournisseurRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        typeFournisseurRepo.deleteById(id);
    }
}