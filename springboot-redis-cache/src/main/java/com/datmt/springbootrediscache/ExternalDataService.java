package com.datmt.springbootrediscache;

import org.springframework.stereotype.Service;

@Service
public class ExternalDataService {
    private String secretData = "s1";

    public String getSecretData() {
        try {
            Thread.sleep(1000);//simulate long running service
        } catch (InterruptedException ex) {
            //
        }
        return secretData;
    }
    public void setSecretData(String secretData) {
        this.secretData = secretData;
    }
}
