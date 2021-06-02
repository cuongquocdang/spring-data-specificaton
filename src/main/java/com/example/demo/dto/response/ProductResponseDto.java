package com.example.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ProductResponseDto implements Serializable {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private String manufacturingDate;

    private String brand;

    private String color;

}

