package com.powerup.square.domain.model;

public class OrderPlates {
    private Long idOrder;
    private Long idPlate;
    private Long amount;

    public OrderPlates(Long idOrder, Long idPlate, Long amount) {
        this.idOrder = idOrder;
        this.idPlate = idPlate;
        this.amount = amount;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdPlate() {
        return idPlate;
    }

    public void setIdPlate(Long idPlate) {
        this.idPlate = idPlate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
