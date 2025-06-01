package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Depense;
import ma.casascolarisation.services.DepenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/depenses")
@RequiredArgsConstructor
public class DepenseController {

    private final DepenseService depenseService;

    @GetMapping
    public List<Depense> findAll() {
        return depenseService.findAll();
    }

    @GetMapping("/{id}")
    public Depense findById(@PathVariable Long id) {
        return depenseService.findById(id);
    }

    @PostMapping
    public Depense save(@RequestBody Depense obj) {
        return depenseService.save(obj);
    }

    @PutMapping("/{id}")
    public Depense update(@PathVariable Long id, @RequestBody Depense updated) {
        updated.setId(id);
        return depenseService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        depenseService.delete(id);
    }
}
