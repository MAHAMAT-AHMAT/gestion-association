package ma.casascolarisation.dto;

import lombok.Data;

@Data
public class DonSimpleDto {
    private Long id;
    private Double montant;
    private String devise;
    private String donateurNom;
}
