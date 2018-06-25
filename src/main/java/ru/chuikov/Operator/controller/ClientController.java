package ru.chuikov.Operator.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.chuikov.Operator.entity.Client;

@RestController
@RequestMapping("/client")
public class ClientController {

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Client getClient(ModelMap model)
    {
        return emptyClient();
    }

    private Client emptyClient() {
        Client client=new Client();
        client.setId(1);
        client.setFirstName("Vasa");
        client.setLastName("Ivanov");
        client.setNumber("89001234567");

        return client;
    }
}
