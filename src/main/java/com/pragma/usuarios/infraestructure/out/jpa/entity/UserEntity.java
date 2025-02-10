package com.pragma.usuarios.infraestructure.out.jpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    @Column(name = "number_document")
    private String numberDocument;

    private String phone;

    private Date birthdate;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name="id_rol")
    private RolEntity role;

    @Column(name="id_restaurant")
    private Long restaurantId;

}
