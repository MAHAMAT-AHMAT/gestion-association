package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Parent;
import ma.casascolarisation.services.ParentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @GetMapping
    public List<Parent> getAll() {
        return parentService.findAll();
    }

    @GetMapping("/{id}")
    public Parent getById(@PathVariable Long id) {
        return parentService.findById(id);
    }

    @PostMapping
    public Parent create(@RequestBody Parent obj) {
        return parentService.save(obj);
    }

    @PutMapping("/{id}")
    public Parent update(@PathVariable Long id, @RequestBody Parent updated) {
        updated.setId(id);
        return parentService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        parentService.delete(id);
    }
}
