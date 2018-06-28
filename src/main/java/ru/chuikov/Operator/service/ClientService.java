package ru.chuikov.Operator.service;

import ru.chuikov.Operator.entity.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAll();
    Client getById(long id);
    Client save(Client client);
    void remove(long id);
    void add(Client client);
}
