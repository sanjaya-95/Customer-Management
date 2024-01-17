package com.example.shoppingnet.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="item")
@Data
public class Item {

    @Id
    @Column(name = "item_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Column(name = "balance_qty", length = 100, nullable = false)
    private double balanceQty;

    @Column(name = "supplier_price", length = 100, nullable = false)
    private double supplierPrice;

    @Column(name = "selling_price", length = 100, nullable = false)
    private double sellingPrice;

    @Column(name = "active_state", columnDefinition = "TINYINT default 1")
    private boolean activeStatus;
}
