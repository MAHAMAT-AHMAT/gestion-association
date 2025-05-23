package ma.casascolarisation.services;

import ma.casascolarisation.entities.Communication;

import java.util.List;

public interface CommunicationService {
    List<Communication> findAll();
    Communication findById(Long id);
    Communication save(Communication obj);
    void delete(Long id);
}
