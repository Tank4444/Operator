package ru.chuikov.Operator.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tariff")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Tariff  {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    private long id;

    @Column(name = "priceSMS")
    private int priceSMS;

    @Column(name = "period")
    private int period;

    @Column(name = "priceInc")
    private int priceInc;

    @Column(name = "timeLastUpdate", nullable = false)
    private Date dateLastUpdate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tariff")
    private Set<Client> clients;

    public Tariff() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public int getPriceSMS() {
        return priceSMS;
    }

    public void setPriceSMS(int priceSMS) {
        this.priceSMS = priceSMS;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPriceInc() {
        return priceInc;
    }

    public void setPriceInc(int priceInc) {
        this.priceInc = priceInc;
    }

    public Date getDateLastUpdate() {
        return dateLastUpdate;
    }

    public void setDateLastUpdate(Date dateLastUpdate) {
        this.dateLastUpdate = dateLastUpdate;
    }
}
