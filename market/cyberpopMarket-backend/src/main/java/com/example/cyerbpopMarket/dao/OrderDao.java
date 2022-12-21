package com.example.cyerbpopMarket.dao;

import com.example.cyerbpopMarket.pojo.AssetPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<AssetPojo> showFloorPriceForErc1155(String tokenaAddress,String tokenbAddress, String tokenId,Long timestamp) {
        List list = new ArrayList();
        String sql = "select a.tokenAaddress,a.price/a.num as price,a.order_hash from orderprice a left join(select firstHashOrder,timestamp from matchedorderinfo) b on a.order_hash = b.firstHashOrder where a.order_hash = b.firstHashOrder and a.tokenAaddress = ? and a.tokenBaddress = ? and a. tokenId = ? and b.timestamp >= ? - 86400 order by b.timestamp";
        SqlRowSet sqlRowser = jdbcTemplate.queryForRowSet (sql,new Object[]{tokenaAddress,tokenbAddress,tokenId,timestamp});
        while (sqlRowser.next ()) {
            AssetPojo assetPojo = new AssetPojo();
            assetPojo.setTokenAddress(sqlRowser.getString ("tokenAaddress"));
            assetPojo.setNum(sqlRowser.getString("price"));
            list.add (assetPojo);
        }
        return list;
    }

    public List<AssetPojo> showFloorPriceForErc721(String tokenaAddress,String tokenbAddress, String tokenId,Long timestamp) {
        List list = new ArrayList();
        String sql = "select a.tokenAaddress,a.price,a.order_hash from orderprice a left join(select firstHashOrder,timestamp from matchedorderinfo) b on a.order_hash = b.firstHashOrder where a.order_hash = b.firstHashOrder and a.tokenAaddress = ? a.tokenBaddress = ? and a. tokenId = ? and b.timestamp >= ? - 86400 and b.tokenAaddress =? order by b.timestamp";
        SqlRowSet sqlRowser = jdbcTemplate.queryForRowSet (sql,new Object[]{tokenaAddress,tokenbAddress,tokenId,timestamp});
        while (sqlRowser.next ()) {
            AssetPojo assetPojo = new AssetPojo();
            assetPojo.setTokenAddress(sqlRowser.getString ("tokenAaddress"));
            assetPojo.setNum(sqlRowser.getString("price"));
            list.add (assetPojo);
        }
        return list;
    }

    public String getTotalSale(String tokenAddress,Long timestamp){
        String sql = "select sum(a.price)  from orderprice a where a.tokenAaddress = ? and order_hash in (select firstHashOrder as order_hash from matchedorderinfo where timestamp >= unix_timestamp() - ?)";
        String result = jdbcTemplate.queryForObject(sql,new Object[]{tokenAddress,timestamp},String.class);
        return  result;
    }

    public String getTotalSaleForErc721(Long timestamp){
        String sql = "select count(num) from orderprice where num =0 and  order_hash in (select firstHashOrder as order_hash from matchedorderinfo where timestamp >= unix_timestamp() - ?)";
        String result = jdbcTemplate.queryForObject(sql,new Object[]{timestamp},String.class);
        return result;
    }
    public String getTotalSaleForErc1155(Long timestamp){
        String sql = "select count(num) from orderprice where num = !0 and  order_hash in (select firstHashOrder as order_hash from matchedorderinfo where timestamp >= unix_timestamp() - ?)";
        String result = jdbcTemplate.queryForObject(sql,new Object[]{timestamp},String.class);
        return result;
    }

    public boolean insertOrderdata(String txHash,String orderHash,String assetData, String orderData,String callData,String  signature,String timesTamp,String order_hash) {
        final String sql = " insert into orderinfo(txHash,orderHash,assetData,orderData,callData,signature,timesTamp,order_hash)values(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,txHash,orderHash,assetData,orderData,callData,signature,timesTamp,order_hash);
        return true;
    }

    public boolean insertOrderState(String callAddress,String order_hash,int state){
        final String sql = " insert into orderstate(callAddress,order_hash,state )values(?,?,?)";
        jdbcTemplate.update(sql,callAddress,order_hash,state);
        return true;
    }

    public boolean insertOrderCancelData(String order_hash, String txHash, String timeStap,String calladdress){
        final  String sql = "insert into cancelorder(order_hash,txHash,timestap,calladdress)values(?,?,?,?)";
        jdbcTemplate.update(sql,order_hash,txHash, timeStap,calladdress);
        return  true;
    }

    public boolean insertOrderMatchedData(String firstCaller,String  seccondcaller, String firstHashOrder,String seccondHashOrder,String timestamp){
        final  String sql = "insert into matchedorderinfo(firstHashOrder,seccondHashOrder,firstcaller,seccondcaller,timestamp)values(?,?,?,?,?)";
        jdbcTemplate.update(sql,firstHashOrder,seccondHashOrder,firstCaller,seccondcaller ,timestamp);
        return  true;
    }

    public boolean insertOrderHashInfo(String orderHash,String maker,String salt,Long ListingTime,Long expirationTime,String offer){
        final  String sql = "insert into orderhashinfo(order_hash,maker,salt,ListingTime,expirationTime,offer)values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,orderHash,maker,salt,ListingTime,expirationTime,offer);
        return  true;
    }

    public boolean insertOrderPrice(String order_hash,String tokenAaddress,String price,String tokenBaddress,String tokenId,int num){
        final  String sql = "insert into orderprice(order_hash,tokenAaddress,price,tokenBaddress,tokenId,num)values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,order_hash,tokenAaddress,price,tokenBaddress,tokenId,num);
        return  true;
    }

    public  boolean updateOrderState(String order_hash,int state){
        String sql = "UPDATE orderstate set state = ? where order_hash = ? ";
        jdbcTemplate.update(sql,new Object[] {state,order_hash});
        return  true;
    }

}
