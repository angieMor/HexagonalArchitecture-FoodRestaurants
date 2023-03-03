package com.powerup.square.infraestructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "plate")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlateEntity {
    @GeneratedValue(strategy = SEQUENCE)
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "price", nullable = false)
    private Long price;
    @ManyToOne
    @JoinColumn(name = "id_Restaurant", nullable = false)
    private RestaurantEntity restaurant;
    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private CategoryEntity category;
    @Column(name = "urlImage", nullable = false)
    private String urlImage;
    @Column(name = "active", nullable = false)
    private boolean active;


}
