package ma.casascolarisation.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference("eleve-parents")
    @ManyToOne
    private Eleve eleve;

    private String nom;
    private String prenom;
    private String profession;
    private String telephone;
    private String email;

    @Enumerated(EnumType.STRING)
    private LienParente lienParente;

    public enum LienParente {
        PERE,
        MERE,
        SOEUR,
        FRERE,
        AUTRE
    }
}
