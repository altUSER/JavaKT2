package com.company.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class CUser {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    public UUID id;

    @Column(name = "login")
    public String login;

    @Column(name = "name")
    public String name;

    @Column(name = "sex")
    public boolean sex; //false = male, true = female

    @Column(name = "date_of_birth", columnDefinition = "DATE")
    public LocalDate dateOfBirth;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<COrder> orders;

    public List<COrder> getOrders() { return orders; }
    public void setOrders(List<COrder> orders) { this.orders = orders; }

    public CUser(){
        id = null;
        sex = false;
        dateOfBirth = LocalDate.now();
        login = "";
    }

    public CUser(UUID id, String login, String name, boolean sex, LocalDate dateOfBirth){
        this.id = id;
        this.login = login;
        this.name = name;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
    }

}
