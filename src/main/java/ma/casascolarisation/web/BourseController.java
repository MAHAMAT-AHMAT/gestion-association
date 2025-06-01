package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Bourse;
import ma.casascolarisation.services.BourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bourses")
@RequiredArgsConstructor
public class BourseController {

    private final BourseService bourseService;

    @GetMapping
    public List<Bourse> findAll() {
        return bourseService.findAll();
    }

    @GetMapping("/{id}")
    public Bourse findById(@PathVariable Long id) {
        return bourseService.findById(id);
    }

    @PostMapping
    public Bourse save(@RequestBody Bourse obj) {
        return bourseService.save(obj);
    }

    @PutMapping("/{id}")
    public Bourse update(@PathVariable Long id, @RequestBody Bourse updated) {
        updated.setId(id);
        return bourseService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bourseService.delete(id);
    }
}
