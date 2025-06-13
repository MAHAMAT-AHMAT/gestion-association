package ma.casascolarisation.dto;

import lombok.Data;

@Data
public class ParentDto {
    private Long id;
    private String nom;
    private String prenom;
    private String profession;
    private String telephone;
    private String email;
    private String lienParente; // "PERE", "MERE", etc.
    private Long eleveId; // <-- pour POST/PUT
    private EleveSimpleDto eleve; // <-- pour GET (affichage)
}
