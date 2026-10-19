package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface IAgenceService {
    public Agence ajouterAgence(Agence agence);
    public Agence modifierAgence(Agence agence);
    public Agence getAgenceById(Long id);
    public List<Agence> getAllAgence();
    public Agence supprimerAgenceById(Long id);
}
