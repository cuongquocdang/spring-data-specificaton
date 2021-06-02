package com.example.demo.controller;

import com.example.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "list-products"})
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public String getListProducts(Model model) {
        model.addAttribute("title", "List Products");
        model.addAttribute("products", productService.getAll());
        return "list-products";
    }


}
