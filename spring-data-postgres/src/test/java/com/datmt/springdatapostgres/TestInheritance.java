package com.datmt.springdatapostgres;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datmt.springdatapostgres.model.IPhone;
import com.datmt.springdatapostgres.model.PixelPhone;
import com.datmt.springdatapostgres.repository.CommonRepository;

@SpringBootTest
public class TestInheritance {

    @Autowired
    private CommonRepository commonRepository;

    @Test
    @DisplayName("test creating inheritance with two phones")
    public void runTest() {

        var iphone = new IPhone();
        iphone.setModel("X");
        iphone.setWithIcloud(false);
        iphone.setBrand("Apple");
        iphone.setName("iPhone X");
        iphone.setPrice(3000);

        commonRepository.save(iphone);

        var nexus = new PixelPhone();
        nexus.setModel("Pixel 10");
        nexus.setPrice(1000);
        nexus.setName("Nexus 10");
        nexus.setGooglePhotoIncluded(true);

        commonRepository.save(nexus);
    }

}
