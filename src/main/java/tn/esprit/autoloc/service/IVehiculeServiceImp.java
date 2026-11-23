package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class IVehiculeServiceImp implements IVehiculeService {
    //@Autowired
    //private VehiculeRepository vehiculeRepository;
    private final VehiculeRepository vehiculeRepository;
    private final EquipementRepository equipementRepository;
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

    // Vehicule est le propriétaire de la relation @ManyToMany
    // (Equipement est le côté inverse : mappedBy = "equipements")
    @Override
    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule).orElse(null);
        Equipement equipement = equipementRepository.findById(idEquipement).orElse(null);
        if (vehicule != null && equipement != null && !vehicule.getEquipements().contains(equipement)) {
            vehicule.getEquipements().add(equipement);
            vehiculeRepository.save(vehicule);
        }
        return vehicule;
    }

    @Override
    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule).orElse(null);
        Equipement equipement = equipementRepository.findById(idEquipement).orElse(null);
        if (vehicule != null && equipement != null) {
            vehicule.getEquipements().remove(equipement);
            vehiculeRepository.save(vehicule);
        }
        return vehicule;
    }
}
