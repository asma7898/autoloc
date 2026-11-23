package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    LocalDate dateSignature;
    BigDecimal montantTotal;
    Boolean valide;
    @OneToOne
    Reservation reservation;
    @JsonIgnore
    @OneToMany(mappedBy = "c",cascade = CascadeType.ALL)
    List<Paiement> p_list= new ArrayList<>();
}
