package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ProductSearchRequestDto implements Serializable {

    private String name;

    private String description;

    private String priceFrom;

    private String priceTo;

    private String manufacturingDateFrom;

    private String manufacturingDateTo;

    private String brand;

    private String color;

}
