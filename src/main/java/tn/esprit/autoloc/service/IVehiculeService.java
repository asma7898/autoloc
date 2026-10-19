package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

public interface IVehiculeService {
    public  Vehicule ajouterVehicule(Vehicule vehicule);
    public Vehicule modifierVehicule(Vehicule vehicule);
    public Vehicule getVehiculeById(Long id);
    public List<Vehicule> getAllVehicule();
    public void supprimerVehiculeById(Long id);

}
