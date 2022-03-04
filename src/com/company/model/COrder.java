package com.company.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "goods_in_orders", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "good_id"))
    List<CGood> goods;

    @Column(name = "date", columnDefinition = "DATE")
    public Date date;


    public UUID getId(){ return id; }
    public CUser getOwner(){ return owner; }
    public void setOwner(CUser owner){ this.owner = owner; }
    public  List<CGood> getGoods() { return goods; }

    public COrder(){
        this.id = null;
        this.owner = null;
    }

    public COrder(UUID id, CUser owner, Date date){
        this.id = id;
        this.owner = owner;
        this.date = date;
    }
}
