package com.powerup.square.application.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.util.HashMap;
import java.util.List;


public class UsersPin {

    private Long idClient;
    private Long pin;

    public static HashMap<Long, HashMap<String, Object>> clientList;

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getPin() {
        return pin;
    }

    public void setPin(Long pin) {
        this.pin = pin;
    }
}
