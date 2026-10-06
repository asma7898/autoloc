package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String nom;
    String ville;
    String adresse;
    String telephone;
    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Employe> employes = new ArrayList<>();
    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Vehicule> vehicules = new ArrayList<>();



}