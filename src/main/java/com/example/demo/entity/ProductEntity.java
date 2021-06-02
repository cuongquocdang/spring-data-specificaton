package com.example.demo.entity;

import com.example.demo.enumeration.BrandEnumeration;
import com.example.demo.enumeration.ColorEnumeration;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String description;

    private Double price;

    @Column(name = "manufacturing_date")
    private LocalDate manufacturingDate;

    @Enumerated(EnumType.STRING)
    private BrandEnumeration brand;

    @Enumerated(EnumType.STRING)
    private ColorEnumeration color;

}
