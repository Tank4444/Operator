package ru.chuikov.Operator.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.chuikov.Operator.entity.Client;
import ru.chuikov.Operator.entity.Tariff;
import ru.chuikov.Operator.service.SMSNotificationService;
import ru.chuikov.Operator.service.TariffService;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class UpdatePriceComponent {

    @Autowired
    private TariffService tariffService;
    @Autowired
    private SMSNotificationService smsNotificationService;


    @Scheduled(cron = "0 * * * * *")
    public void reportCurrentTime() {
        List<Tariff> tariffs=tariffService.getAll();
        Date date=new Date();
        for(Tariff tariff: tariffs)
        {
            Long olderTime =tariff.getDateLastUpdate().getTime(); // заданная дата в Unix-epoch в мс
            Long wrightNow =  date.getTime();
            Long result = (wrightNow - olderTime)/60000;
            if(result>=tariff.getPeriod())
            {
                tariff.setPriceSMS(tariff.getPriceSMS()+tariff.getPriceInc());
                tariff.setDateLastUpdate(date);
                tariffService.save(tariff);
                for(Client client:tariff.getClients())
                {
                    smsNotificationService.sendNewTariffInfo(client,tariff);
                }
            }
        }

    }
}
