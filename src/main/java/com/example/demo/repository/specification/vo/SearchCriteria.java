package com.example.demo.repository.specification.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchCriteria {

    private String key;

    private String value;

    private String fromValue;

    private String toValue;

    private SearchOperation operation;

}
