package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;

public interface IClientService {
    public Client ajouterClient(Client client);
    public Client modifierClient(Client client);
    public Client getClientById(Long id);
    public List<Client> getAllClient();
    public Client supprimerClientById(Long id);
}
