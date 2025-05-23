package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Bourse;
import ma.casascolarisation.repositories.BourseRepo;
import ma.casascolarisation.services.BourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BourseServiceImpl implements BourseService {

    private final BourseRepo bourseRepo;

    @Override
    public List<Bourse> findAll() {
        return bourseRepo.findAll();
    }

    @Override
    public Bourse findById(Long id) {
        return bourseRepo.findById(id).orElse(null);
    }

    @Override
    public Bourse save(Bourse obj) {
        return bourseRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        bourseRepo.deleteById(id);
    }
}