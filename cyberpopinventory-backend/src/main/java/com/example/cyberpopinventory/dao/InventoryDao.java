package com.example.cyberpopinventory.dao;


import com.example.cyberpopinventory.pojo.BasicDispositionPojo;
import com.example.cyberpopinventory.pojo.DispositionPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InventoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public boolean insertInventoryPeroid(int peroid,String startTIme,String endTime) {
        final String sql = " insert into inventoryperoid(peroid,startTIme,endTime)values(?,?,?)";
        jdbcTemplate.update(sql,peroid,startTIme,endTime);
        return true;
    }

    public boolean initInsertDispositionData(String contractAddress, int num, String price, int peroid,String id){
        final String sql = " insert into disposition(contractAddress,num,price,peroid,id)values(?,?,?,?,?)";
        jdbcTemplate.update(sql,contractAddress,num,price,peroid,id);
        return true;
    }

    public boolean updateNUm(int num_,int peroid,String contractAddress,String id){
        String sql = "UPDATE disposition set num = num + ? where peroid = ? and contractAddress =? and id = ?";
        jdbcTemplate.update(sql,new Object[] {num_,peroid,contractAddress,id});
        return  true;
    }

    public boolean resetNUm(int num_,int peroid,String contractAddress,String id){
        String sql = "UPDATE disposition set num = ? where peroid = ? and contractAddress =? and id = ?";
        jdbcTemplate.update(sql,new Object[] {num_,peroid,contractAddress,id});
        return  true;
    }


    public int getLastPeroid(){
        String sql = "select peroid from inventoryperoid a where  unix_timestamp() > a.startTIme";
        return  jdbcTemplate.queryForObject(sql,int.class);
    }


    public List getDispositionData(int peroid){
        List dispositionList = new ArrayList();
        String sql = "select contractAddress,num,price,peroid,id from disposition where peroid = ?";
        SqlRowSet sqlRowser = jdbcTemplate.queryForRowSet (sql,new Object[]{peroid});
        while (sqlRowser.next ()) {
            DispositionPojo dispositionPojo = new DispositionPojo();
            String price = sqlRowser.getString("price");
            String contractAddress = sqlRowser.getString("contractAddress");
            String id = sqlRowser.getString("id");
            int num = sqlRowser.getInt("num");
            dispositionPojo.setNum(num);
            dispositionPojo.setContractAddress(contractAddress);
            dispositionPojo.setPeroid(peroid);
            dispositionPojo.setPrice(price);
            dispositionPojo.setId(id);
            dispositionList.add(dispositionPojo);
        }
        return  dispositionList;
    }

    public BasicDispositionPojo getDispositionData(int peroid,String contractAddress,String id){
        BasicDispositionPojo basicDispositionPojo = new BasicDispositionPojo();
        String sql = "select num,price,id from disposition where peroid = ? and contractAddress =? and id = ?";
        SqlRowSet sqlRowser = jdbcTemplate.queryForRowSet (sql,new Object[]{peroid,contractAddress,id});
        while (sqlRowser.next ()) {
            String price = sqlRowser.getString("price");
            int num = sqlRowser.getInt("num");
            basicDispositionPojo.setNum(num);
            basicDispositionPojo.setPrice(price);
            basicDispositionPojo.setId(id);
        }
        return basicDispositionPojo;
    }


}


