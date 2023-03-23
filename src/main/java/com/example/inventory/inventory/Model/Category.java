package com.example.inventory.inventory.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data // para implementar los getters y setters de lombok
@Entity
@Table(name = "category")
public class Category implements Serializable{
    
    private static final long serialVersionUID = 1L;

    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable=false, length = 150)
    private String name;

    @Column(name = "description", nullable=false, length = 300)
    private String description;

}
