package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.dto.BourseDto;
import ma.casascolarisation.dto.BourseMapper;
import ma.casascolarisation.entities.Bourse;
import ma.casascolarisation.entities.Eleve;
import ma.casascolarisation.entities.Don;
import ma.casascolarisation.entities.AnneeScolaire;
import ma.casascolarisation.services.BourseService;
import ma.casascolarisation.services.EleveService;
import ma.casascolarisation.services.DonService;
import ma.casascolarisation.services.AnneeScolaireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bourses")
@RequiredArgsConstructor
public class BourseController {


}