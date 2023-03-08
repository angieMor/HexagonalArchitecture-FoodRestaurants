package com.powerup.square.domain.model;

public class Employee {
    private Long idOwner;
    private Long idUser;
    private String field;


    public Employee(Long idOwner, Long idUser, String field) {
        this.idOwner = idOwner;
        this.idUser = idUser;
        this.field = field;
    }



    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
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
