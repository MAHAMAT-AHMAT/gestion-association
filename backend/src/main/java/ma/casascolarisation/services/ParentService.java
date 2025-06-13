package ma.casascolarisation.services;

import ma.casascolarisation.entities.Parent;
import java.util.List;

public interface ParentService {
    List<Parent> findAll();
    Parent findById(Long id);
    Parent save(Parent parent);
    void delete(Long id);
}
