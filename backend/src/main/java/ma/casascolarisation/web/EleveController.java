package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Eleve;
import ma.casascolarisation.services.EleveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @PostMapping("/upload-photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) {
        try {
            String uploadsDir = System.getProperty("user.dir") + "/uploads/eleves/";
            Files.createDirectories(Paths.get(uploadsDir));

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadsDir, fileName);
            file.transferTo(filePath.toFile());

            // Ici on retourne bien le chemin pour que le frontend puisse l'utiliser
            return ResponseEntity.ok("/uploads/eleves/" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur lors de l'upload");
        }
    }


}
