package ma.casascolarisation.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomUtilisateur;

    private String motDePasse;
    private String photo;

    private String email;

    private LocalDateTime dateCreation;

    private LocalDateTime derniereConnexion;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ADMIN,
        SUPERVISEUR,
        COMPTABLE,
        DONATEUR
    }
    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDateTime.now();
        this.derniereConnexion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.derniereConnexion = LocalDateTime.now();
    }
}
