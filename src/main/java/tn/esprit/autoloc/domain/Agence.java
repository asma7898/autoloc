package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nom;
    String ville;
    String adresse;
    String telephone;
    @JsonIgnore
    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Employe> employes = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Vehicule> vehicules = new ArrayList<>();



}