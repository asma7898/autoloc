package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.domain.StatutReservation;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    LocalDate dateDebut;
    LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    StatutReservation statut;
    @OneToOne(mappedBy = "reservation")
    Contrat contrat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicule_id")
    Vehicule vehicule;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "client_id")
    Client client;
}
