package ma.casascolarisation.services;

import ma.casascolarisation.entities.Document;

import java.util.List;

public interface DocumentService {
    List<Document> findAll();
    Document findById(Long id);
    Document save(Document obj);
    void delete(Long id);
}
