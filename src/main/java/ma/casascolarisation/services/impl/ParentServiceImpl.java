package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Eleve;
import ma.casascolarisation.entities.Parent;
import ma.casascolarisation.repositories.EleveRepo;
import ma.casascolarisation.repositories.ParentRepo;
import ma.casascolarisation.services.ParentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepo parentRepo;
    private final EleveRepo eleveRepo;

    @Override
    public List<Parent> findAll() {
        return parentRepo.findAll();
    }

    @Override
    public Parent findById(Long id) {
        return parentRepo.findById(id).orElse(null);
    }

    @Override
    public Parent save(Parent obj) {
        // Reload eleve from database if it exists
        if (obj.getEleve() != null && obj.getEleve().getId() != null) {
            Eleve eleve = eleveRepo.findById(obj.getEleve().getId()).orElse(null);
            if (eleve != null) {
                obj.setEleve(eleve);
            }
        }

        return parentRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        parentRepo.deleteById(id);
    }
}
