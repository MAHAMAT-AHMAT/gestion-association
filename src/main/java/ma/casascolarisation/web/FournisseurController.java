package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Fournisseur;
import ma.casascolarisation.services.FournisseurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fournisseurs")
@RequiredArgsConstructor
public class FournisseurController {

    private final FournisseurService fournisseurService;

    @GetMapping
    public ResponseEntity<List<Fournisseur>> findAll() {
        return ResponseEntity.ok(fournisseurService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> findById(@PathVariable Long id) {
        Fournisseur fournisseur = fournisseurService.findById(id);
        return ResponseEntity.ok(fournisseur);
    }

    @PostMapping
    public ResponseEntity<Fournisseur> save(@RequestBody Fournisseur obj) {
        Fournisseur savedFournisseur = fournisseurService.save(obj);
        return ResponseEntity.status(201).body(savedFournisseur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fournisseur> update(@PathVariable Long id, @RequestBody Fournisseur updated) {
        updated.setId(id);
        Fournisseur savedFournisseur = fournisseurService.save(updated);
        return ResponseEntity.ok(savedFournisseur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fournisseurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}