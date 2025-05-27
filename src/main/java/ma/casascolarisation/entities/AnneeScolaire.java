package ma.casascolarisation.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnoreProperties("annee")
    @OneToMany(mappedBy = "annee", cascade = CascadeType.ALL)
    private List<Bourse> bourses;

    @JsonIgnoreProperties("annee")
    @OneToMany(mappedBy = "annee", cascade = CascadeType.ALL)
    private List<Don> dons;

    @JsonIgnoreProperties("annee")
    @OneToMany(mappedBy = "annee", cascade = CascadeType.ALL)
    private List<Eleve> eleves;

    @JsonIgnoreProperties("annee")
    @OneToMany(mappedBy = "annee", cascade = CascadeType.ALL)
    private List<Depense> depenses;

    @JsonIgnoreProperties("annee")
    @OneToMany(mappedBy = "annee", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
