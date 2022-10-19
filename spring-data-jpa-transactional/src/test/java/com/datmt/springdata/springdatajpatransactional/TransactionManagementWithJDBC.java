package com.datmt.springdata.springdatajpatransactional;

import com.datmt.springdata.springdatajpatransactional.config.AppConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionManagementWithJDBC {

    private final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    private final DataSource datasource = (DataSource) context.getBean("default_datasource");
    private final PlatformTransactionManager txManager = (DataSourceTransactionManager) context.getBean("datasource_tx_manager");

    @BeforeAll
    void setup() throws SQLException {
        Connection connection = datasource.getConnection();
        connection.createStatement().executeUpdate("UPDATE bank_user set balance = 1000");
        connection.close();
    }

    @Test
    void test_query_existing_initial_data() throws SQLException {
        validateAliceAndBobAmount(1000L, 1000L);
    }

    @Test
    void test_transfer_money_with_exception() {
        try (Connection connection = datasource.getConnection()) {
            //withdraw credits from Alice's account
            connection.createStatement().executeUpdate("UPDATE bank_user SET balance = 900 WHERE name = 'Alice'");

            //Simulate an exception
            if (true) {
                throw new RuntimeException("Opps! System crashes...");
            }
            connection.createStatement().executeUpdate("UPDATE bank_user SET balance = 1100 WHERE name = 'Bob'");
        } catch (SQLException ex) {
            //
        } catch (RuntimeException ex) {
            //
            System.out.println("Got a runtime exception");
        }

    }

    @Test
    void test_transfer_money_with_platform_transaction_management_using_jdbc_template() {

        try {
            var definition = new DefaultTransactionDefinition();
            var status = txManager.getTransaction(definition);
            var jdbcTemplate = new JdbcTemplate(datasource);
            //withdraw credits from Alice's account
            jdbcTemplate.update("UPDATE bank_user SET balance = 900 WHERE name = 'Alice'");
            //Simulate an exception
            if (true) {
                throw new RuntimeException("Opps! System crashes...");
            }
            jdbcTemplate.update("UPDATE bank_user SET balance = 1100 WHERE name = 'Bob'");

            txManager.commit(status);
        } catch (RuntimeException e) {
            //
        }

        validateAliceAndBobAmount(1000L, 1000L);

    }


    @Test
    void test_transfer_money_with_transaction_management() {
        try (Connection connection = datasource.getConnection()) {
            connection.setAutoCommit(false);
            //withdraw credits from Alice's account
            connection.createStatement().executeUpdate("UPDATE bank_user SET balance = 900 WHERE name = 'Alice'");

            //Simulate an exception
            if (true) {
                throw new RuntimeException("Opps! System crashes...");
            }
            connection.createStatement().executeUpdate("UPDATE bank_user SET balance = 1100 WHERE name = 'Bob'");
            connection.commit();
        } catch (SQLException ex) {
            //
        } catch (RuntimeException ex) {
            //
            System.out.println("Got a runtime exception");
        }

        validateAliceAndBobAmount(1000L, 1000L);

    }

    private void validateAliceAndBobAmount(long expectedAliceBalance, long expectedBobBalance) {
        try (

                Connection connection = datasource.getConnection();
        ) {
            //get alice details
            var aliceStatement = connection.createStatement().executeQuery("SElECT * FROM bank_user WHERE name = 'Alice'");
            var bobStatement = connection.createStatement().executeQuery("SElECT * FROM bank_user WHERE name = 'Bob'");

            if (aliceStatement.next() && bobStatement.next()) {
                var aliceAmount = aliceStatement.getLong("balance");
                var bobAmount = bobStatement.getLong("balance");

                assertEquals(expectedAliceBalance, aliceAmount, "Alice still has 1000 credits");
                assertEquals(expectedBobBalance, bobAmount, "Bob still has 1000 credits");
            } else {
                fail("Bad data in the database");
            }

        } catch (SQLException ex) {
            //
        }
    }


}
