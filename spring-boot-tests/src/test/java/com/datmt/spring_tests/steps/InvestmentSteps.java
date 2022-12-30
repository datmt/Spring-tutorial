package com.datmt.spring_tests.steps;

import com.datmt.spring_tests.ContainerBase;
import com.datmt.spring_tests.model.Investment;
import com.datmt.spring_tests.repository.InvestmentRepository;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvestmentSteps extends ContainerBase {

    @Autowired
    private InvestmentRepository investmentRepository;

    @io.cucumber.java.en.Given("^I current have no investment$")
    public void iCurrentHaveNoInvestment() {
        investmentRepository.deleteAll();
    }

    @io.cucumber.java.en.Given("^I create an investment with \"([^\"]*)\"  with the name \"([^\"]*)\"$")
    public void iCreateAnInvestmentWithWithTheName(String amountString, String name) throws Throwable {
        var amount = Float.parseFloat(amountString);

        var investment = Investment.builder().amount(amount).name(name).build();

        investmentRepository.save(investment);
    }

    @io.cucumber.java.en.Then("^I should have \"([^\"]*)\" investments with the total of \"([^\"]*)\" in my investment account$")
    public void iShouldHaveAInvestmentsWithTheTotalOfInMyInvestmentAccount(String count, String total) throws Throwable {
        var expectedTotalInvestment = Integer.parseInt(count);
        var expectedTotalAmount = Float.parseFloat(total);

        var allInvestments = investmentRepository.findAll();

        assert allInvestments.size() == expectedTotalInvestment;
        var actualTotalAmount = allInvestments.stream().map(Investment::getAmount).reduce(0f, Float::sum);
        Assertions.assertEquals(expectedTotalAmount, actualTotalAmount);
    }
}
