package com.company.model;


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

    public UUID getId() {return id;}

    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public boolean getSex() {return sex;}
    public void setSex(boolean sex) {this.sex = sex;}

    public LocalDate getDateOfBirth() {return dateOfBirth;}
    public void setDateOfBirth() {this.sex = sex;}


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
