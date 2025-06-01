package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Eleve;
import ma.casascolarisation.services.EleveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eleves")
@RequiredArgsConstructor
public class EleveController {

    private final EleveService eleveService;

    @GetMapping
    public List<Eleve> findAll() {
        return eleveService.findAll();
    }

    @GetMapping("/{id}")
    public Eleve findById(@PathVariable Long id) {
        return eleveService.findById(id);
    }

    @PostMapping
    public Eleve save(@RequestBody Eleve obj) {
        return eleveService.save(obj);
    }

    @PutMapping("/{id}")
    public Eleve update(@PathVariable Long id, @RequestBody Eleve updated) {
        updated.setId(id);
        return eleveService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eleveService.delete(id);
    }
}
