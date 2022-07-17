package com.ecommerce.library.service.impl;

import com.ecommerce.library.model.City;
import com.ecommerce.library.repository.CityRepository;
import com.ecommerce.library.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }
}
