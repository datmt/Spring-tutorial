package com.datmt.spring.batch.processor;

import com.datmt.spring.batch.models.OriginalUser;
import com.datmt.spring.batch.models.TargetUser;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class UserProcessor implements ItemProcessor<OriginalUser, TargetUser> {
    @Override
    public TargetUser process(OriginalUser item) throws Exception {
        var birthday = item.getBirthday();
        //calculate age from the birthday yyyy-mm-dd to now
        var age = LocalDate.now().getYear() - birthday.toLocalDate().getYear();

        return new TargetUser(
                item.getPassword(),
                item.getEmail(),
                item.getFirstName(),
                age,
                item.getLastName(),
                item.getCreatedAt(),
                item.getUpdatedAt()
        );
    }
}
