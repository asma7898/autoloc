package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.domain.StatutReservation;

import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    LocalDate dateDebut;
    LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    StatutReservation statut;
    @JsonIgnore
    @OneToOne(mappedBy = "reservation")
    Contrat contrat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicule_id")
    Vehicule vehicule;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "client_id")
    Client client;
}
