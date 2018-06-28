package ru.chuikov.Operator.service;

import ru.chuikov.Operator.entity.Client;
import ru.chuikov.Operator.entity.Tariff;

public interface SMSNotificationService {
    void sendNewTariffInfo(Client client, Tariff tariff);
}
