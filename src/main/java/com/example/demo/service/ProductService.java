package com.example.demo.service;

import com.example.demo.dto.response.ProductResponseDto;
import com.example.demo.repository.specification.vo.SearchCriteria;

import java.util.List;

public interface ProductService {

    List<ProductResponseDto> getAll();

    List<ProductResponseDto> searchByCriteria(List<SearchCriteria> SearchCriteria);

}
