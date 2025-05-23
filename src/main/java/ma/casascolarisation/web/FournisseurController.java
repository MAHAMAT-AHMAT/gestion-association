package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Fournisseur;
import ma.casascolarisation.services.FournisseurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fournisseurs")
@RequiredArgsConstructor
public class FournisseurController {

    private final FournisseurService fournisseurService;

    @GetMapping
    public List<Fournisseur> findAll() {
        return fournisseurService.findAll();
    }

    @GetMapping("/{id}")
    public Fournisseur findById(@PathVariable Long id) {
        return fournisseurService.findById(id);
    }

    @PostMapping
    public Fournisseur save(@RequestBody Fournisseur obj) {
        return fournisseurService.save(obj);
    }

    @PutMapping("/{id}")
    public Fournisseur update(@PathVariable Long id, @RequestBody Fournisseur updated) {
        updated.setId(id);
        return fournisseurService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        fournisseurService.delete(id);
    }
}
