package com.datmt.springbootrediscache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secret")
public class SecretController {

    @Autowired
    ExternalDataService dataService;

    @Autowired
    CachedDataService cachedDataService;

    @PutMapping
    public void setSecret(@RequestBody String newSecret) {
       dataService.setSecretData(newSecret);
    }

    @GetMapping("/cached")
    public String getCachedData() {
       return cachedDataService.getSecretData();
    }

    @GetMapping("/no-cached")
    public String noCachedData() {
        return dataService.getSecretData();
    }


}
