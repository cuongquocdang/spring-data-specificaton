package com.example.demo.config;

import com.example.demo.entity.ProductEntity;
import com.example.demo.enumeration.BrandEnumeration;
import com.example.demo.enumeration.ColorEnumeration;
import com.example.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@AllArgsConstructor
public class DataSourceConfig {

    private final ProductRepository productRepository;

    @PostConstruct
    public void initData() {
        // SAMSUNG
        createProductByBrand(BrandEnumeration.SAMSUNG);
        // APPLE
        createProductByBrand(BrandEnumeration.APPLE);
        // SONY
        createProductByBrand(BrandEnumeration.SONY);
        // LG
        createProductByBrand(BrandEnumeration.LG);
        // XIAOMI
        createProductByBrand(BrandEnumeration.XIAOMI);
        // HUAWEI
        createProductByBrand(BrandEnumeration.HUAWEI);
        // ASUS
        createProductByBrand(BrandEnumeration.ASUS);
        // OPPO
        createProductByBrand(BrandEnumeration.OPPO);
        // LENOVO
        createProductByBrand(BrandEnumeration.LENOVO);
        // NOKIA
        createProductByBrand(BrandEnumeration.NOKIA);
    }

    private void createProductByBrand(BrandEnumeration brand) {
        List<ProductEntity> products = IntStream.range(1, 11)
                .mapToObj(number -> ProductEntity.builder()
                        .name(brand.name() + randomText().toUpperCase())
                        .description("Description " + randomText())
                        .price(randomNumber() + 0.5)
                        .manufacturingDate(randomDate())
                        .brand(brand)
                        .color(randomColor())
                        .build())
                .collect(Collectors.toList());

        productRepository.saveAll(products);
    }

    private String randomText() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    private int randomNumber() {
        int max = 1000;
        int min = 1;
        return (int) ((Math.random() * (max - min)) + min);
    }

    private LocalDate randomDate() {
        LocalDate start = LocalDate.of(2000, Month.JANUARY, 1);
        LocalDate end = LocalDate.now();

        long startEpochDay = start.toEpochDay();
        long endEpochDay = end.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    private ColorEnumeration randomColor() {
        return ColorEnumeration.values()[new Random().nextInt(ColorEnumeration.values().length)];
    }

}
