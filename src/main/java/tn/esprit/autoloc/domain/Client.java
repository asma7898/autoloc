package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nom;
    String prenom;
    String email;
    String telephone;
    String numPermis;
    LocalDate dateInscription;
    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY,mappedBy = "client")
    List<Reservation> reservations;
}
