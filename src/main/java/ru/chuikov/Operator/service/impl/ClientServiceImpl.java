package ru.chuikov.Operator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chuikov.Operator.entity.Client;
import ru.chuikov.Operator.repository.ClientRepository;
import ru.chuikov.Operator.service.ClientService;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client getById(long id) {
        return clientRepository.findOne(id);
    }

    public Client save(Client client) {
        return clientRepository.saveAndFlush(client);
    }

    public void remove(long id) {
        clientRepository.deleteById(id);
    }

    public void add(Client clientNew) {
        clientRepository.saveAndFlush(clientNew);
    }
}
