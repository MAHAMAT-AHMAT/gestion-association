package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.NiveauScolaire;
import ma.casascolarisation.services.NiveauScolaireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/niveauScolaires")
@RequiredArgsConstructor
public class NiveauScolaireController {

    private final NiveauScolaireService niveauScolaireService;

    @GetMapping
    public List<NiveauScolaire> findAll() {
        return niveauScolaireService.findAll();
    }

    @GetMapping("/{id}")
    public NiveauScolaire findById(@PathVariable Long id) {
        return niveauScolaireService.findById(id);
    }

    @PostMapping
    public NiveauScolaire save(@RequestBody NiveauScolaire obj) {
        return niveauScolaireService.save(obj);
    }

    @PutMapping("/{id}")
    public NiveauScolaire update(@PathVariable Long id, @RequestBody NiveauScolaire updated) {
        updated.setId(id);
        return niveauScolaireService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        niveauScolaireService.delete(id);
    }
}
