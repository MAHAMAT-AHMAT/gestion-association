package ma.casascolarisation.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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

    @JsonBackReference("donateur-dons")
    @ManyToOne
    private Donateur donateur;

    @JsonBackReference("annee-dons")
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
