package com.example.demo.service.impl;

import com.example.demo.dto.response.ProductResponseDto;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.specification.ProductSpecification;
import com.example.demo.repository.specification.vo.SearchCriteria;
import com.example.demo.service.ProductService;
import com.example.demo.util.ConvertUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponseDto> getAll() {
        return ConvertUtil.convertListEntityToListResponse(productRepository.findAll(), ProductResponseDto.class);
    }

    @Override
    public List<ProductResponseDto> searchByCriteria(List<SearchCriteria> listSearchCriteria) {
        ProductSpecification productSpecification = new ProductSpecification(listSearchCriteria);
        return ConvertUtil.convertListEntityToListResponse(
                productRepository.findAll(productSpecification), ProductResponseDto.class);
    }

}
