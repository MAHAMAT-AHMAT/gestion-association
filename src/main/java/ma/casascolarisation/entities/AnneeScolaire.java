package ma.casascolarisation.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class AnneeScolaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private boolean estActive;

    @JsonIgnore
    @OneToMany(mappedBy = "annee", cascade = CascadeType.ALL)
    private List<Bourse> bourses;

    @JsonIgnore
    @OneToMany(mappedBy = "annee", cascade = CascadeType.ALL)
    private List<Don> dons;

    @JsonIgnore
    @OneToMany(mappedBy = "annee", cascade = CascadeType.ALL)
    private List<Eleve> eleves;

    @JsonIgnore
    @OneToMany(mappedBy = "annee", cascade = CascadeType.ALL)
    private List<Depense> depenses;

    @JsonIgnore
    @OneToMany(mappedBy = "annee", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
