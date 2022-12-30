package com.datmt.spring_tests.integration;

import com.datmt.spring_tests.ContainerBase;
import com.datmt.spring_tests.model.Investment;
import com.datmt.spring_tests.repository.InvestmentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InvestmentRepositoryTest extends ContainerBase {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Test
    @DisplayName("Create investment should be ok")
    @Order(1)
    void testCreate() {
        var investment = Investment.builder()
                .name("Buy some bitcoin")
                .amount(1000f)
                .build();
        var savedInvestment = investmentRepository.save(investment);

        Assertions.assertNotNull(savedInvestment.getId());
    }

    @Test
    @DisplayName("Find all investment should be ok")
    @Order(2)
    void testFind() {
        var foundInvestment = investmentRepository.findByName("Buy some bitcoin");

        Assertions.assertTrue(foundInvestment.isPresent());
    }

    @Test
    @DisplayName("Find investments greater than specific amount should work")
    @Order(3)
    void testFindAllByAmountGreaterThan() {
        investmentRepository.save(Investment.builder()
                .name("Buy some eth")
                .amount(100f)
                .build());
        investmentRepository.save(Investment.builder()
                .name("Buy some eth")
                .amount(600f)
                .build());


        var foundInvestment = investmentRepository.findAllByAmountGreaterThan(500f);

        Assertions.assertTrue(foundInvestment.isPresent());
        Assertions.assertEquals(2, foundInvestment.get().size());
    }
}
