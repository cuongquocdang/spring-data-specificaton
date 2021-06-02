package com.example.demo.api;

import com.example.demo.dto.response.ProductResponseDto;
import com.example.demo.repository.specification.vo.SearchCriteria;
import com.example.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductApi {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponseDto> getAll() {
        return productService.getAll();
    }

    @PostMapping("/search")
    public List<ProductResponseDto> searchByCriteria(@RequestBody ArrayList<SearchCriteria> listSearchCriteria) {
        return productService.searchByCriteria(listSearchCriteria);
    }

}
