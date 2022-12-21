package com.example.boxpool.dao;

import com.example.boxpool.pojo.HistoryPurchasePojo;
import com.example.boxpool.pojo.MerkelPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BoxDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public boolean insertUserData(String account,String price,int randomNum,String timestamp,int award,int peroid,String txHash) {
        final String sql = " insert into usersevent(account,price,randomNum,timestamp,award,peroid,txHash)values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,account,price,randomNum,timestamp,award,peroid,txHash);
        return true;
    }

    public boolean insertRewardData(String account,int randomNum,String timestamp,int peroid,String txHash) {
        final String sql = " insert into reward(account,randomNum,timestamp,peroid,txHash)values(?,?,?,?,?)";
        jdbcTemplate.update(sql,account,randomNum,timestamp,peroid,txHash);
        return true;
    }

    public boolean insterRandomData(String root,int peroid,String randomList){
        final String sql = " insert into lotterylist(root,peroid,randomList)values(?,?,?)";
        jdbcTemplate.update(sql,root,peroid,randomList);
        return true;
    }


    public boolean updateAward(int randmNum,int flag){
        String sql = "UPDATE usersevent set Award = ? where randomNum = ? ";
        jdbcTemplate.update(sql,new Object[] {flag,randmNum});
        return  true;
    }

    public String getRewardNumberList(int peroid){
        String sql = "select randomList from lotterylist where peroid = ?";
        return  jdbcTemplate.queryForObject(sql,new Object[]{peroid},String.class);
    }

//    getRewardList
    public  List getRewardList(int peroid,int award) {
    List merkelPojoList = new ArrayList();
    String sql = "select account,timestamp,randomNum from usersevent where peroid = ? and award=?;";
    SqlRowSet sqlRowser = jdbcTemplate.queryForRowSet (sql,new Object[]{peroid,award});
    while (sqlRowser.next ()) {
        MerkelPojo merkelPojo = new MerkelPojo();
        String account = sqlRowser.getString("account");
        String timestamp = sqlRowser.getString("timestamp");
        int randomNum = sqlRowser.getInt("randomNum");

        merkelPojo.setAccount(account);
        merkelPojo.setTimestamp(timestamp);
        merkelPojo.setRandomNum(randomNum);
        merkelPojoList.add (merkelPojo);
        }
        return merkelPojoList;
    }

    public  List getApplyList(int peroid,int apply) {
        List merkelPojoList = new ArrayList();
        String sql = "select account,timestamp,randomNum from usersevent where peroid = ? and apply=?;";
        SqlRowSet sqlRowser = jdbcTemplate.queryForRowSet (sql,new Object[]{peroid,apply});
        while (sqlRowser.next ()) {
            MerkelPojo merkelPojo = new MerkelPojo();
            String account = sqlRowser.getString("account");
            String timestamp = sqlRowser.getString("timestamp");
            int randomNum = sqlRowser.getInt("randomNum");
            merkelPojo.setAccount(account);
            merkelPojo.setTimestamp(timestamp);
            merkelPojo.setRandomNum(randomNum);
            merkelPojoList.add (merkelPojo);
        }
        return merkelPojoList;
    }


    public  List getMemkerProof(int peroid) {
        List merkelPojoList = new ArrayList();
        String sql = "select account,timestamp,randomNum from usersevent where peroid = ? ;";
        SqlRowSet sqlRowser = jdbcTemplate.queryForRowSet (sql,new Object[]{peroid});
        while (sqlRowser.next ()) {
            MerkelPojo merkelPojo = new MerkelPojo();
            String account = sqlRowser.getString("account");
            String timestamp = sqlRowser.getString("timestamp");
            int randomNum = sqlRowser.getInt("randomNum");

            merkelPojo.setAccount(account);
            merkelPojo.setTimestamp(timestamp);
            merkelPojo.setRandomNum(randomNum);
            merkelPojoList.add (merkelPojo);
        }
        return merkelPojoList;
        
    }

    public int numberCount(int peroid){
        final  String sql = "select count(randomNum) from usersevent where peroid = ?";
        return   jdbcTemplate.queryForObject(sql,new Object[]{peroid},int.class);
    }


    public List getHistoryPurchase(String account_){
        List HistoryPurchasePojoList = new ArrayList();
        String sql = "select account,timestamp,randomNum,peroid from usersevent where account = ? order by randomNum desc";
        SqlRowSet sqlRowser = jdbcTemplate.queryForRowSet (sql,new Object[]{account_});
        while (sqlRowser.next ()) {
            HistoryPurchasePojo historyPurchasePojo = new HistoryPurchasePojo();
            String account = sqlRowser.getString("account");
            String timestamp = sqlRowser.getString("timestamp");
            int peroid = sqlRowser.getInt("peroid");
            int randomNum = sqlRowser.getInt("randomNum");

            historyPurchasePojo.setAccount(account);
            historyPurchasePojo.setTimestamp(timestamp);
            historyPurchasePojo.setRandomNum(randomNum);
            historyPurchasePojo.setPeroid(peroid);
            HistoryPurchasePojoList.add (historyPurchasePojo);
        }
        return HistoryPurchasePojoList;
    }
}


