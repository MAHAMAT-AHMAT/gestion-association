package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Utilisateur;
import ma.casascolarisation.services.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    @GetMapping
    public List<Utilisateur> findAll() {
        return utilisateurService.findAll();
    }

    @GetMapping("/{id}")
    public Utilisateur findById(@PathVariable Long id) {
        return utilisateurService.findById(id);
    }

    @PostMapping
    public Utilisateur save(@RequestBody Utilisateur obj) {
        return utilisateurService.save(obj);
    }

    @PutMapping("/{id}")
    public Utilisateur update(@PathVariable Long id, @RequestBody Utilisateur updated) {
        updated.setId(id);
        return utilisateurService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        utilisateurService.delete(id);
    }
    @PostMapping("/upload-photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) {
        try {
            String uploadsDir = System.getProperty("user.dir") + "/uploads/utilisateurs/";
            Files.createDirectories(Paths.get(uploadsDir));

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadsDir, fileName);
            file.transferTo(filePath.toFile());

            // *** LE CHEMIN RETOURNÉ ***
            return ResponseEntity.ok("/uploads/utilisateurs/" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur lors de l'upload");
        }
    }


}
