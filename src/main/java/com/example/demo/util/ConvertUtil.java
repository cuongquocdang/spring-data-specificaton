package com.example.demo.util;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertUtil {

    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
    }

    public static <E, RESP> RESP convertEntityToResponse(E entity, Class<RESP> clazz) {
        return modelMapper.map(entity, clazz);
    }

    public static <E, RESP> List<RESP> convertListEntityToListResponse(List<E> entities, Class<RESP> clazz) {
        return entities.stream().map(entity -> convertEntityToResponse(entity, clazz)).collect(Collectors.toList());
    }

}
