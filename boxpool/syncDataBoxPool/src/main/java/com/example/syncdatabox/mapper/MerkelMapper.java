package com.example.syncdatabox.mapper;

import com.example.syncdatabox.pojo.MerkelPojo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MerkelMapper implements RowMapper<MerkelPojo> {
    @Override
    public MerkelPojo mapRow(ResultSet rs, int rowNum) throws SQLException {

        String account = rs.getString("account");
        String timestamp = rs.getString("timestamp");
        int randomNum = rs.getInt("randomNum");

        MerkelPojo merkelPojo = new MerkelPojo();
        merkelPojo.setAccount(account);
        merkelPojo.setTimestamp(timestamp);
        merkelPojo.setRandomNum(randomNum);

        return merkelPojo;

    }
}
