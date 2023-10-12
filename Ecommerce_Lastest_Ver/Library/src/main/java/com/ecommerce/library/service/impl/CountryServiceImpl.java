package com.ecommerce.library.service.impl;

import com.ecommerce.library.model.Country;
import com.ecommerce.library.repository.CountryRepository;
import com.ecommerce.library.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}
