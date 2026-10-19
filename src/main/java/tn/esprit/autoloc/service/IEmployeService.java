package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;

public interface IEmployeService {
    public Employe ajouterEmploye(Employe employe);
    public Employe modifierEmploye(Employe employe);
    public Employe getEmployeById(Long id);
    public List<Employe> getAllEmploye();
    public Employe supprimerEmployeById(Long id);
}

