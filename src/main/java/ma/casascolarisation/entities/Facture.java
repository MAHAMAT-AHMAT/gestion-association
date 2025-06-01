package ma.casascolarisation.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference("transaction-factures")
    @ManyToOne
    private Transaction transaction;

    @JsonBackReference("donateur-factures")
    @ManyToOne
    private Donateur donateur;

    private BigDecimal montantTotal;

    private LocalDate dateEmission;

    private String cheminPdf;

    private String logoEntreprise;
}
