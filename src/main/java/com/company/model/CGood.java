package com.company.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "goods")
public class CGood {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    public UUID id;

    @Column(name = "name")
    public String name;

    @Column(name = "price")
    public double price;

    @Column(name = "category")
    public String category;


    @OneToMany(mappedBy = "good", fetch = FetchType.EAGER)
    private List<COrder> orders;


    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    public double getPrice(){ return price; }
    public void setPrice(double price){ this.price = price; }

    public String getCategory(){ return category; }
    public void setCategory(String category){ this.category = category; }


    public CGood(){
        this.id = null;
        this.name = "";
        this.price = 0;
        this.category = "";
    }

    public CGood(UUID id, String name, double price, String category){
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
