package com.datmt.springdata.springdatajpatransactional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankTransferService extends JdbcDaoSupport {


    @Transactional
    public void transfer(String from, String to, Long amount) {
        withdraw(from, amount);
        deposit(to, amount);
    }

    @Transactional
    public void sendAndSplitToOtherAccounts(String senderName, String recipientName, Long amount,
                                            String recipientSavingAccount, String recipientInvestingAccount) {
        transfer(senderName, recipientName, amount);
        transfer(recipientName, recipientSavingAccount, amount / 4);
        transfer(recipientName, recipientInvestingAccount, amount / 4);
    }

    public void resetBalance() {
        getJdbcTemplate().update("UPDATE bank_user SET balance = 1000");
    }

    private BankUser getUser(String username) {
        return getJdbcTemplate().query("SELECT * FROM bank_user WHERE name = '" + username + "'", new RowMapper<BankUser>() {
            @Override
            public BankUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                var bankUser = new BankUser();
                bankUser.setId(rs.getLong("id"));
                bankUser.setName(rs.getString("name"));
                bankUser.setBalance(rs.getLong("balance"));
                return bankUser;
            }
        }).get(0);
    }

    private void withdraw(String user, Long amount) {
        var bankUser = getUser(user);
        var newBalance = bankUser.getBalance() - amount;
        getJdbcTemplate().update("UPDATE bank_user SET balance = ? WHERE name = ?", newBalance, user);
    }

    private void deposit(String user, Long amount) {
        if (amount == 25)
            throw new RuntimeException("I cannot get that much!");
        var bankUser = getUser(user);
        var newBalance = bankUser.getBalance() + amount;

        getJdbcTemplate().update("UPDATE bank_user SET balance = ? WHERE name = ?", newBalance, user);
    }

}
