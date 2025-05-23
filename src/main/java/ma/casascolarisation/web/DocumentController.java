package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Document;
import ma.casascolarisation.services.DocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping
    public List<Document> findAll() {
        return documentService.findAll();
    }

    @GetMapping("/{id}")
    public Document findById(@PathVariable Long id) {
        return documentService.findById(id);
    }

    @PostMapping
    public Document save(@RequestBody Document obj) {
        return documentService.save(obj);
    }

    @PutMapping("/{id}")
    public Document update(@PathVariable Long id, @RequestBody Document updated) {
        updated.setId(id);
        return documentService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        documentService.delete(id);
    }
}
