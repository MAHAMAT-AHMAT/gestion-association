package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.AnneeScolaire;
import ma.casascolarisation.services.AnneeScolaireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anneeScolaires")
@RequiredArgsConstructor
public class AnneeScolaireController {

    private final AnneeScolaireService anneeScolaireService;

    @GetMapping
    public List<AnneeScolaire> findAll() {
        return anneeScolaireService.findAll();
    }

    @GetMapping("/{id}")
    public AnneeScolaire findById(@PathVariable Long id) {
        return anneeScolaireService.findById(id);
    }

    @PostMapping
    public AnneeScolaire save(@RequestBody AnneeScolaire obj) {
        return anneeScolaireService.save(obj);
    }

    @PutMapping("/{id}")
    public AnneeScolaire update(@PathVariable Long id, @RequestBody AnneeScolaire updated) {
        updated.setId(id);
        return anneeScolaireService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        anneeScolaireService.delete(id);
    }
}
