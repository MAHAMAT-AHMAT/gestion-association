package ma.casascolarisation.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    private Fournisseur fournisseur;

    @JsonIgnore
    @ManyToOne
    private Eleve eleve;

    @JsonIgnore
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
