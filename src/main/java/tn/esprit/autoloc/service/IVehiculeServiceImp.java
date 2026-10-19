package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class IVehiculeServiceImp implements IVehiculeService {
    //@Autowired
    //private VehiculeRepository vehiculeRepository;
    private final VehiculeRepository vehiculeRepository;
    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }
    @Override
    public Vehicule modifierVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule getVehiculeById(Long id) {
        return vehiculeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vehicule> getAllVehicule() {
        return vehiculeRepository.findAll();
    }

    @Override
    public void supprimerVehiculeById(Long id) {
        vehiculeRepository.deleteById(id);

    }
}
