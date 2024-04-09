package com.draekk.salesmanagement.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String name;

    @OneToMany(mappedBy = "client_id", orphanRemoval = true, cascade = CascadeType.ALL)
    List<Address> addresses;

    @OneToMany(mappedBy = "client_id", orphanRemoval = true, cascade = CascadeType.ALL)
    List<Sale> sales;

    public Client() {
        addresses = new ArrayList<>();
        sales = new ArrayList<>();
    }

    public Client(String name) {
        this.name = name;
    }
}
