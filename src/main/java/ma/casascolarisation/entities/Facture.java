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
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Transaction transaction;

    @JsonIgnore
    @ManyToOne
    private Donateur donateur;

    private BigDecimal montantTotal;

    private LocalDate dateEmission;

    private String cheminPdf;

    private String logoEntreprise;
}
