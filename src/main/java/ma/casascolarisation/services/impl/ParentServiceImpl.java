package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Parent;
import ma.casascolarisation.repositories.ParentRepo;
import ma.casascolarisation.services.ParentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepo parentRepo;

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
        return parentRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        parentRepo.deleteById(id);
    }
}