package ru.chuikov.Operator.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.chuikov.Operator.entity.Client;
import ru.chuikov.Operator.entity.Tariff;
import ru.chuikov.Operator.service.SMSNotificationService;

import java.text.SimpleDateFormat;
import java.util.Date;
@Service
public class SMSNotificationServiceImpl implements SMSNotificationService {
    private static final Logger log = LoggerFactory.getLogger(SMSNotificationService.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    public void sendNewTariffInfo(Client client, Tariff tariff) {
        String notice="";
        notice+=client.getFirstName()+" new price of SMS is "+tariff.getPriceSMS();
        notice+=" what changed in "+dateFormat.format(new Date());
        log.info(notice);
    }
}
