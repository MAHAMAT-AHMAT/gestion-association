package ma.casascolarisation.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Fournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NomTypeFournisseur typeFournisseur;

    public enum NomTypeFournisseur {
        ECOLE,
        SANTE,
        LOISIRS,
        AUTRE
    }

    private String nom;
    private String adresse;
    private String telephone;
    private String email;
    private String notes;

    @JsonIgnore
    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL)
    private List<Eleve> eleves;

    @JsonIgnore
    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL)
    private List<Depense> depenses;
}
