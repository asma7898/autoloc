package tn.esprit.autoloc.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String immatriculation;
    String marque;
    String modele;
    @Enumerated(EnumType.STRING)
    CategorieVehicule categorie;
    BigDecimal tarifJournalier;
    @Enumerated(EnumType.STRING)
    StatutVehicule statut;
    @ManyToMany(fetch=FetchType.EAGER)
    List<Equipement> equipements;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "agence_id")
    Agence agence;
    @OneToMany(mappedBy = "vehicule",fetch = FetchType.LAZY)
    List<Reservation> reservations;

}