package ma.casascolarisation.entities;

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

    private String email;

    private LocalDateTime dateCreation;

    private LocalDateTime derniereConnexion;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Communication> communications;

    public enum Role {
        ADMIN,
        SUPERVISEUR,
        COMPTABLE,
        DONATEUR
    }
}
