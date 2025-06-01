package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Communication;
import ma.casascolarisation.repositories.CommunicationRepo;
import ma.casascolarisation.services.CommunicationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunicationServiceImpl implements CommunicationService {

    private final CommunicationRepo communicationRepo;

    @Override
    public List<Communication> findAll() {
        return communicationRepo.findAll();
    }

    @Override
    public Communication findById(Long id) {
        return communicationRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Communication introuvable pour l'id " + id));
    }

    @Override
    public Communication save(Communication obj) {
        return communicationRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        communicationRepo.deleteById(id);
    }
}