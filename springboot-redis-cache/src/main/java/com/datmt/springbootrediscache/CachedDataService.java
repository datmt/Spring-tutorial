package com.datmt.springbootrediscache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CachedDataService {

    @Autowired
    ExternalDataService dataService;
    @Cacheable(value = "secretData")
    public String getSecretData() {
        return dataService.getSecretData();
    }
}
