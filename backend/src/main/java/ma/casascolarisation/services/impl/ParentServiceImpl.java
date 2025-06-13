package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Parent;
import ma.casascolarisation.entities.Eleve;
import ma.casascolarisation.repositories.ParentRepo;
import ma.casascolarisation.services.ParentService;
import ma.casascolarisation.services.EleveService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepo parentRepo;
    private final EleveService eleveService;

    @Override
    public List<Parent> findAll() {
        return parentRepo.findAll();
    }

    @Override
    public Parent findById(Long id) {
        return parentRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parent introuvable pour l'id " + id));
    }

    @Override
    public Parent save(Parent obj) {
        // Sécurise l'association avec l'élève si besoin
        if (obj.getEleve() != null && obj.getEleve().getId() != null) {
            Eleve eleve = eleveService.findById(obj.getEleve().getId());
            obj.setEleve(eleve);
        }
        return parentRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        parentRepo.deleteById(id);
    }
}
