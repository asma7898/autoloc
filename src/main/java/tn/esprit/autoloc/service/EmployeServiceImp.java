package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EmployeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeServiceImp implements IEmployeService {

    private final EmployeRepository employeRepository;
    private final AgenceRepository agenceRepository;

    @Override
    public Employe ajouterEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public Employe modifierEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public Employe getEmployeById(Long id) {
        return employeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employe> getAllEmploye() {
        return employeRepository.findAll();
    }

    @Override
    public Employe supprimerEmployeById(Long id) {
        Employe employe = getEmployeById(id);
        if (employe != null) {
            employeRepository.deleteById(id);
        }
        return employe;
    }

    // Mutation - étape 1 : désaffecter l'employé de son agence actuelle
    @Override
    @Transactional
    public Employe desaffecterEmploye(Long idEmploye) {
        Employe employe = employeRepository.findById(idEmploye).orElse(null);
        if (employe != null) {
            employe.setAgence(null);
            employeRepository.save(employe);
        }
        return employe;
    }

    // Mutation - étape 2 : affecter l'employé à la nouvelle agence
    @Override
    @Transactional
    public Employe affecterEmploye(Long idEmploye, Long idAgence) {
        Employe employe = employeRepository.findById(idEmploye).orElse(null);
        Agence agence = agenceRepository.findById(idAgence).orElse(null);
        if (employe != null && agence != null) {
            employe.setAgence(agence);
            employeRepository.save(employe);
        }
        return employe;
    }
}
