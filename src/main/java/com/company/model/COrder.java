package com.company.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class COrder {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    public UUID id;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    public CUser owner;

    @ManyToOne
    @JoinColumn(name = "good", nullable = false)
    public CGood good;

    @Column(name = "date", columnDefinition = "timestamp")
    public Date date;


    public UUID getId(){ return id; }
    public CUser getOwner(){ return owner; }
    public void setOwner(CUser owner){ this.owner = owner; }
    public  CGood getGood() { return good; }

    public COrder(){
        this.id = null;
        this.owner = null;
        this.good = null;
        this.date = new Date();
    }

    public COrder(UUID id, CUser owner, CGood good, Date date){
        this.id = id;
        this.owner = owner;
        this.good = good;
        this.date = date;
    }
}
