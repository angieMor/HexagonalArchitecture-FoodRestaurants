package com.powerup.square.domain.model;

import java.util.Date;

public class Order {
    private Long id;
    private Long idClient;
    private Date date;
    private String state;
    private Restaurant restaurant;

    private Long idEmployee;

    public Order(Long id, Long idClient, Date date, String state, Restaurant restaurant, Long idEmployee) {
        this.id = id;
        this.idClient = idClient;
        this.date = date;
        this.state = state;
        this.restaurant = restaurant;
        this.idEmployee = idEmployee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }
}
