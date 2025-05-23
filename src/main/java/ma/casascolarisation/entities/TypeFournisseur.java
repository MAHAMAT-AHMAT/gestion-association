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
public class TypeFournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NomTypeFournisseur nom;

    @JsonIgnore
    @OneToMany(mappedBy = "typeFournisseur", cascade = CascadeType.ALL)
    private List<Fournisseur> fournisseurs;

    public enum NomTypeFournisseur {
        ECOLE,
        SANTE,
        LOISIRS,
        AUTRE
    }
}
