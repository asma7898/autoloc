package tn.esprit.autoloc.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.domain.ModePaiement;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    BigDecimal montant;
    LocalDate datePaiement;

    @Enumerated(EnumType.STRING)
    ModePaiement modePaiement;
    @ManyToOne
    Contrat c;
}