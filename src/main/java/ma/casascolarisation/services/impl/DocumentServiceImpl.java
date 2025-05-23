package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Document;
import ma.casascolarisation.repositories.DocumentRepo;
import ma.casascolarisation.services.DocumentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepo documentRepo;

    @Override
    public List<Document> findAll() {
        return documentRepo.findAll();
    }

    @Override
    public Document findById(Long id) {
        return documentRepo.findById(id).orElse(null);
    }

    @Override
    public Document save(Document obj) {
        return documentRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        documentRepo.deleteById(id);
    }
}