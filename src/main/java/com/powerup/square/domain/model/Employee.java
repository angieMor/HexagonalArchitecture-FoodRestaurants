package com.powerup.square.domain.model;

public class Employee {
    private Restaurant restaurant;
    private Long idUser;
    private String field;


    public Employee(Restaurant restaurant, Long idUser, String field) {
        this.restaurant = restaurant;
        this.idUser = idUser;
        this.field = field;
    }



    public Long getIdRestaurant() {
        return restaurant.getId();
    }

    public Long getIdOwnerRestaurant() {return restaurant.getIdOwner();}

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
