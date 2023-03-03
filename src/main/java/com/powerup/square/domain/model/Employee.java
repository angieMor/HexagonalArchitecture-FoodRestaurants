package com.powerup.square.domain.model;

public class Employee {
    private Long idRestaurant;
    private Long idUser;
    private String field;

    public Employee(Long idRestaurant, Long idUser, String field) {
        this.idRestaurant = idRestaurant;
        this.idUser = idUser;
        this.field = field;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
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
