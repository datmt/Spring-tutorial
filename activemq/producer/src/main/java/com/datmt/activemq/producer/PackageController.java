package com.datmt.activemq.producer;

import com.datmt.activemq.data.Letter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PackageController {

    @Autowired
    JmsTemplate template;
    @GetMapping("/send-package-simple")
    public String sendPackageSimple() {
        for (int i = 0; i < 10; i++) {
            template.convertAndSend("package_queue", "Package #" + i);
        }
        return "packages sent!";
    }
    @GetMapping("/send-package-group")
    public String sendPackageGroup() {
        var gid = UUID.randomUUID().toString();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            template.send("package_queue", session -> {
                var m = session.createTextMessage("Package " + finalI);
                m.setStringProperty("JMSXGroupID", gid);
                return m;
            });
        }
        return "packages sent!";
    }
}
