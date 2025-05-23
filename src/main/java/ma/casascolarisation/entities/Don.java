package ma.casascolarisation.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Don {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Donateur donateur;

    @JsonIgnore
    @ManyToOne
    private AnneeScolaire annee;

    private BigDecimal montant;

    private String devise;

    private LocalDate date;

    private String description;

    private boolean estRecurrent;

    @Enumerated(EnumType.STRING)
    private StatutDon statut;

    @JsonIgnore
    @OneToMany(mappedBy = "don", cascade = CascadeType.ALL)
    private List<Bourse> bourses;

    @JsonIgnore
    @OneToMany(mappedBy = "don", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public enum StatutDon {
        PROMIS,
        RECU,
        AFFECTE
    }
}
