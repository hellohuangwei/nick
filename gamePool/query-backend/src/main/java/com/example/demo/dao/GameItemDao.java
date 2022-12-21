package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GameItemDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean insertUserLodePropetdata(String accountAddress, String gamePoolAddress, String loadTime, String tokenId, String amount) {
        final String sql = "insert into userGameInfo(accountAddress,gamePoolAddress,loadTime,tokenId,amount)values(?,?,?,?,?)";
        jdbcTemplate.update(sql, accountAddress, gamePoolAddress, loadTime, tokenId, amount);
        return true;
    }

}
