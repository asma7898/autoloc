package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    LocalDate dateSignature;
    BigDecimal montantTotal;
    boolean valide;
    @OneToOne
    Reservation reservation;
    @OneToMany(mappedBy = "c",cascade = CascadeType.ALL)
    List<Paiement> p_list= new ArrayList<>();
}
