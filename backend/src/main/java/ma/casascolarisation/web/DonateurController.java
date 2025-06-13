package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Donateur;
import ma.casascolarisation.services.DonateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/donateurs")
@RequiredArgsConstructor
public class DonateurController {

    private final DonateurService donateurService;

    @GetMapping
    public List<Donateur> findAll() {
        return donateurService.findAll();
    }

    @GetMapping("/{id}")
    public Donateur findById(@PathVariable Long id) {
        return donateurService.findById(id);
    }

    @PostMapping
    public Donateur save(@RequestBody Donateur obj) {
        return donateurService.save(obj);
    }

    @PutMapping("/{id}")
    public Donateur update(@PathVariable Long id, @RequestBody Donateur updated) {
        updated.setId(id);
        return donateurService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        donateurService.delete(id);
    }
    @PostMapping("/upload-photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) {
        try {
            // Récupère le dossier courant du projet (d'où tu lances le JAR)
            String uploadsDir = System.getProperty("user.dir") + "/uploads/donateurs/";
            Files.createDirectories(Paths.get(uploadsDir));

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadsDir, fileName);
            file.transferTo(filePath.toFile());

            // Pour le front, le chemin doit rester relatif
            return ResponseEntity.ok("/uploads/donateurs/" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur lors de l'upload");
        }
    }


}
