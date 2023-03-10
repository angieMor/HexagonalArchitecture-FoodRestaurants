package com.powerup.square.domain.model;

public class OrderPlates {
    private Order order;
    private Plate plate;
    private Long amount;

    public OrderPlates(Order order, Plate plate, Long amount) {
        this.order = order;
        this.plate = plate;
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
