package ma.casascolarisation.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Depense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeDepense type;

    @JsonBackReference("fournisseur-depenses")
    @ManyToOne
    private Fournisseur fournisseur;

    @JsonBackReference("eleve-depenses")
    @ManyToOne
    private Eleve eleve;

    @JsonBackReference("annee-depenses")
    @ManyToOne
    private AnneeScolaire annee;

    private BigDecimal montant;

    private LocalDate date;

    private String justificatif;

    private String description;

    public enum TypeDepense {
        SCOLAIRE,
        MEDICALE,
        PARASCOLAIRE,
        AUTRE
    }
}
