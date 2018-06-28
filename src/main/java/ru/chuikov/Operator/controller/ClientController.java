package ru.chuikov.Operator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.chuikov.Operator.entity.Client;
import ru.chuikov.Operator.service.ClientService;
import ru.chuikov.Operator.service.TariffService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private Client cl;
    @Autowired
    private ClientService clientService;

    @Autowired
    private TariffService tariffService;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET )
    @ResponseBody
    public List<Client> getAllClient()
    {
        List<Client>r=clientService.getAll();
        return r;
    }

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET )
    public Client getClient(@PathVariable("id") long clientId)
    {
        return clientService.getById(clientId);
    }

    @RequestMapping(value = "/save",method = RequestMethod.GET )
    public Client saveClient(@RequestParam("id") long id,
                             @RequestParam("fn") String fist,
                             @RequestParam("last") String last,
                             @RequestParam("num") String num,
                             @RequestParam("tar") int tar
                            )
    {
        cl=new Client();
        cl.setId(id);
        cl.setFirstName(fist);
        cl.setLastName(last);
        cl.setNumber(num);
        cl.setTariff(tariffService.getById(tar));
        return clientService.save(cl);
    }

    @RequestMapping(value = "/new",method = RequestMethod.GET )
    public void createClienrt(@RequestParam("fn") String fist,
                              @RequestParam("last") String last,
                              @RequestParam("num") String num,
                              @RequestParam("tar") int tar
                              )
    {
        cl=new Client();
        cl.setFirstName(fist);
        cl.setLastName(last);
        cl.setNumber(num);
        cl.setTariff(tariffService.getById(tar));
        clientService.add(cl);
    }


    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET )
    public void delete(@PathVariable("id") long clientId)
    {
        clientService.remove(clientId);
    }

}
