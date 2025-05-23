package ma.casascolarisation.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Eleve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    private LocalDate dateNaissance;
    private String lieuNaissance;

    @JsonIgnore
    @ManyToOne
    private Fournisseur fournisseur;

    @JsonIgnore
    @ManyToOne
    private NiveauScolaire niveau;

    @JsonIgnore
    @ManyToOne
    private AnneeScolaire annee;

    private LocalDateTime dateInscription;

    private String informationsMedicales;
    private String photo;

    @Enumerated(EnumType.STRING)
    private StatutEleve statut;

    @JsonIgnore
    @OneToMany(mappedBy = "eleve", cascade = CascadeType.ALL)
    private List<Bourse> bourses;

    @JsonIgnore
    @OneToMany(mappedBy = "eleve", cascade = CascadeType.ALL)
    private List<Parent> parents;

    @JsonIgnore
    @OneToMany(mappedBy = "eleve", cascade = CascadeType.ALL)
    private List<Depense> depenses;

    public enum StatutEleve {
        ORPHELIN,
        DEMUNIS,
        HANDICAPE,
        AUTRE
    }
}
