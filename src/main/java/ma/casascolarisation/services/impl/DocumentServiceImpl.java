package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Document;
import ma.casascolarisation.repositories.*;
import ma.casascolarisation.services.DocumentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepo documentRepo;
    private final DonRepo donRepo;
    private final DepenseRepo depenseRepo;
    private final BourseRepo bourseRepo;
    private final EleveRepo eleveRepo;

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
        // 👉 Ajoute la validation ici AVANT de sauvegarder :
        switch (obj.getEntite()) {
            case DON:
                if (!donRepo.existsById(obj.getIdEntiteLiee())) {
                    throw new IllegalArgumentException("Don introuvable pour l'ID " + obj.getIdEntiteLiee());
                }
                break;
            case DEPENSE:
                if (!depenseRepo.existsById(obj.getIdEntiteLiee())) {
                    throw new IllegalArgumentException("Dépense introuvable pour l'ID " + obj.getIdEntiteLiee());
                }
                break;
            case BOURSE:
                if (!bourseRepo.existsById(obj.getIdEntiteLiee())) {
                    throw new IllegalArgumentException("Bourse introuvable pour l'ID " + obj.getIdEntiteLiee());
                }
                break;
            case ELEVE:
                if (!eleveRepo.existsById(obj.getIdEntiteLiee())) {
                    throw new IllegalArgumentException("Élève introuvable pour l'ID " + obj.getIdEntiteLiee());
                }
                break;
            default:
                throw new IllegalArgumentException("Type d'entité inconnue");
        }
        // Si tout est OK, on sauvegarde le document :
        return documentRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        documentRepo.deleteById(id);
    }
}
