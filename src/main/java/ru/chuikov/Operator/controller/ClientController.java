package ru.chuikov.Operator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.chuikov.Operator.entity.Client;
import ru.chuikov.Operator.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET )
    public List<Client> getAllClient()
    {
        return clientService.getAll();
    }

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET )
    public Client getClient(@PathVariable("id") long clientId)
    {
        return clientService.getById(clientId);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST )
    public Client saveClient(@RequestBody Client client)
    {
        return clientService.save(client);
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST )
    public void create(@RequestBody Client client)
    {
        clientService.add(client);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST )
    public void delete(@PathVariable("id") long clientId)
    {
        clientService.remove(clientId);
    }

}
