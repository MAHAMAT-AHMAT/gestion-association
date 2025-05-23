package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.TypeFournisseur;
import ma.casascolarisation.services.TypeFournisseurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/typeFournisseurs")
@RequiredArgsConstructor
public class TypeFournisseurController {

    private final TypeFournisseurService typeFournisseurService;

    @GetMapping
    public List<TypeFournisseur> findAll() {
        return typeFournisseurService.findAll();
    }

    @GetMapping("/{id}")
    public TypeFournisseur findById(@PathVariable Long id) {
        return typeFournisseurService.findById(id);
    }

    @PostMapping
    public TypeFournisseur save(@RequestBody TypeFournisseur obj) {
        return typeFournisseurService.save(obj);
    }

    @PutMapping("/{id}")
    public TypeFournisseur update(@PathVariable Long id, @RequestBody TypeFournisseur updated) {
        updated.setId(id);
        return typeFournisseurService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        typeFournisseurService.delete(id);
    }
}
