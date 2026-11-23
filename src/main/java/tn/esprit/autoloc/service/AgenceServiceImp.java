package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AgenceServiceImp implements IAgenceService {

    private final AgenceRepository agenceRepository;
    private final VehiculeRepository vehiculeRepository;

    @Override
    public Agence ajouterAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence modifierAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence getAgenceById(Long id) {
        return agenceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Agence> getAllAgence() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence supprimerAgenceById(Long id) {
        Agence agence = getAgenceById(id);
        if (agence != null) {
            agenceRepository.deleteById(id);
        }
        return agence;
    }

    // Enregistre l'agence et ses véhicules, puis affecte chaque véhicule à l'agence
    // (Vehicule est le propriétaire de la relation : c'est lui qui porte la colonne agence_id)
    @Override
    @Transactional
    public Agence ajouterAgenceEtAffecterVehicules(Agence agence) {
        List<Vehicule> vehicules = agence.getVehicules();
        Agence agenceEnregistree = agenceRepository.save(agence);
        for (Vehicule vehicule : vehicules) {
            vehicule.setAgence(agenceEnregistree);
            vehiculeRepository.save(vehicule);
        }
        return agenceEnregistree;
    }
}
