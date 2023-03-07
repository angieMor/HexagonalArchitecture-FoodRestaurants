package com.powerup.square.infraestructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Column(name= "id_restaurant")
    private Long idRestaurant;

    @ManyToOne
    @JoinColumn(name = "id_restaurant", nullable = false, insertable = false, updatable = false)
    private RestaurantEntity restaurant;

    @Column(name = "field", nullable = false)
    private String field;

}
