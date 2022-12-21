package com.example.cyerbpopMarket.page;

import com.example.cyerbpopMarket.pojo.AssetPojo;
import com.example.cyerbpopMarket.pojo.MathcedHistoryPojo;
import com.example.cyerbpopMarket.pojo.OrderInfoPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class PageOrder {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List list;

    public List<OrderInfoPojo> findAllbyPage(int pageNum, int pageRow) {
        list = new ArrayList();
        int starter = (pageNum-1)*pageRow;
        String sql = "select a.order_hash, b.signature, b.orderHash, b.orderData, b.callData, b.assetData , b.txHash,b.timesTamp from orderstate a left join ( select txHash, signature , timesTamp,orderHash, oId, orderData,callData,assetData,order_hash from orderinfo) b on a.order_hash = b.order_hash left join(select order_hash, expirationTime,ListingTime from orderhashinfo) c on a.order_hash = c.order_hash   where a.state = 1 and  unix_timestamp() > c.ListingTime  and unix_timestamp()  < c.expirationTime  order by oId asc limit ?,?";
        SqlRowSet sqlRowser = jdbcTemplate.queryForRowSet (sql,new Object[]{starter,pageRow});
        while (sqlRowser.next ()) {
            OrderInfoPojo orderInfoPojo = new OrderInfoPojo();
            orderInfoPojo.setTxHash(sqlRowser.getString ("txHash"));
            orderInfoPojo.setOrderHash(sqlRowser.getString ("orderHash"));
            orderInfoPojo.setOrderData(sqlRowser.getString ("orderData"));
            orderInfoPojo.setCallData(sqlRowser.getString ("callData"));
            orderInfoPojo.setSignature(sqlRowser.getString ("signature"));
            orderInfoPojo.setTimesTamp(sqlRowser.getString("timesTamp"));
            orderInfoPojo.setAssetData(sqlRowser.getString("assetData"));
            orderInfoPojo.setOrder_hash(sqlRowser.getString("order_hash"));
            list.add (orderInfoPojo);
        }
        return list;
    }

    public List<OrderInfoPojo> showAllProgressingOrderInfo2(int pageNum, int pageRow) {
        list = new ArrayList();
        int starter = (pageNum-1)*pageRow;
        String sql = "select a.order_hash, b.signature, b.orderHash, b.orderData, b.callData, b.assetData , b.txHash,b.timesTamp from orderstate a left join ( select txHash, signature , timesTamp,orderHash, oId, orderData,callData,assetData,order_hash from orderinfo) b on a.order_hash = b.order_hash left join(select order_hash, expirationTime,ListingTime from orderhashinfo) c on a.order_hash = c.order_hash   where a.state = 1 and  unix_timestamp() > c.ListingTime  and unix_timestamp()  < c.expirationTime  order by oId desc limit ?,?";
        SqlRowSet sqlRowser = jdbcTemplate.queryForRowSet (sql,new Object[]{starter,pageRow});
        while (sqlRowser.next ()) {
            OrderInfoPojo orderInfoPojo = new OrderInfoPojo();
            orderInfoPojo.setTxHash(sqlRowser.getString ("txHash"));
            orderInfoPojo.setOrderHash(sqlRowser.getString ("orderHash"));
            orderInfoPojo.setOrderData(sqlRowser.getString ("orderData"));
            orderInfoPojo.setCallData(sqlRowser.getString ("callData"));
            orderInfoPojo.setSignature(sqlRowser.getString ("signature"));
            orderInfoPojo.setTimesTamp(sqlRowser.getString("timesTamp"));
            orderInfoPojo.setAssetData(sqlRowser.getString("assetData"));
            orderInfoPojo.setOrder_hash(sqlRowser.getString("order_hash"));
            list.add (orderInfoPojo);
        }
        return list;
    }



    public List<OrderInfoPojo> showMyProgressingOrderInfo(int pageNum, int pageRow,String callAddress) {
        list = new ArrayList();
        int starter = (pageNum-1)*pageRow;
        String sql = "select a.order_hash, b.signature, b.orderHash, b.orderData, b.callData, b.assetData , b.txHash,b.timesTamp from orderstate a left join ( select txHash, signature , timesTamp,orderHash, oId, orderData,callData,assetData,order_hash from orderinfo) b on a.order_hash = b.order_hash left join(select order_hash, expirationTime,ListingTime from orderhashinfo) c on a.order_hash = c.order_hash   where a.state = 1 and  unix_timestamp() > c.ListingTime  and unix_timestamp()  < c.expirationTime  and a.callAddress = ? order by oId asc limit ?,?";
        SqlRowSet sqlRowser = jdbcTemplate.queryForRowSet (sql,new Object[]{callAddress,starter,pageRow});
        while (sqlRowser.next ()) {
            OrderInfoPojo orderInfoPojo = new OrderInfoPojo();
            orderInfoPojo.setTxHash(sqlRowser.getString ("txHash"));
            orderInfoPojo.setOrderHash(sqlRowser.getString ("orderHash"));
            orderInfoPojo.setOrderData(sqlRowser.getString ("orderData"));
            orderInfoPojo.setCallData(sqlRowser.getString ("callData"));
            orderInfoPojo.setSignature(sqlRowser.getString ("signature"));
            orderInfoPojo.setTimesTamp(sqlRowser.getString("timesTamp"));
            orderInfoPojo.setAssetData(sqlRowser.getString("assetData"));
            orderInfoPojo.setOrder_hash(sqlRowser.getString("order_hash"));
            list.add (orderInfoPojo);
        }
        return list;
    }

    public List<OrderInfoPojo> showSecondaryMarketOrderInfo(int pageNum, int pageRow) {
        list = new ArrayList();
        int starter = (pageNum-1)*pageRow;
        String sql = "select a.order_hash, b.signature, b.orderHash, b.orderData, b.callData, b.assetData , b.txHash,b.timesTamp from orderstate a left join ( select txHash, signature , timesTamp,orderHash, oId, orderData,callData,assetData,order_hash from orderinfo) b on a.order_hash = b.order_hash left join(select maker, order_hash, expirationTime,ListingTime from orderhashinfo) c on a.order_hash = c.order_hash   where a.state = 1 and  unix_timestamp() > c.ListingTime  and unix_timestamp()  < c.expirationTime and c.maker != '0x7291030263771b40731d6bc6b352358d23f5737f' order by oId asc limit ?,?";
        SqlRowSet sqlRowser = jdbcTemplate.queryForRowSet (sql,new Object[]{starter,pageRow});
        while (sqlRowser.next ()) {
            OrderInfoPojo orderInfoPojo = new OrderInfoPojo();
            orderInfoPojo.setTxHash(sqlRowser.getString ("txHash"));
            orderInfoPojo.setOrderHash(sqlRowser.getString ("orderHash"));
            orderInfoPojo.setOrderData(sqlRowser.getString ("orderData"));
            orderInfoPojo.setCallData(sqlRowser.getString ("callData"));
            orderInfoPojo.setSignature(sqlRowser.getString ("signature"));
            orderInfoPojo.setTimesTamp(sqlRowser.getString("timesTamp"));
            orderInfoPojo.setAssetData(sqlRowser.getString("assetData"));
            orderInfoPojo.setOrder_hash(sqlRowser.getString("order_hash"));
            list.add (orderInfoPojo);
        }
        return list;
    }

    public List<MathcedHistoryPojo> showHistoryMatchedOrderInfo(int pageNum, int pageRow, String tokenAddress, String tokenId) {
        list = new ArrayList();
        int starter = (pageNum-1)*pageRow;
        String sql = "select a.tokenAaddress,a.price,a.tokenBaddress,a.tokenId,a.num,b.timestamp, b.firstcaller,b.seccondcaller from orderprice a left join (select firstHashOrder,firstcaller,seccondcaller,timestamp from matchedorderinfo) b on a.order_hash = b.firstHashOrder left join(select order_hash,state from orderstate) c on a.order_hash = c.order_hash   where a.order_hash = b.firstHashOrder and a.tokenBaddress= ? and a.tokenId= ? and c.state =2 order by b.timestamp desc limit ?,?";
        SqlRowSet sqlRowser = jdbcTemplate.queryForRowSet (sql,new Object[]{tokenAddress,tokenId,starter,pageRow});
        while (sqlRowser.next ()) {
            MathcedHistoryPojo mathcedHistoryPojo = new MathcedHistoryPojo();
            mathcedHistoryPojo.setTokenAaddress(sqlRowser.getString ("tokenAaddress"));
            mathcedHistoryPojo.setPrice(sqlRowser.getString ("price"));
            mathcedHistoryPojo.setTokenBaddress(sqlRowser.getString ("tokenBaddress"));
            mathcedHistoryPojo.setTokenId(sqlRowser.getString ("tokenId"));
            mathcedHistoryPojo.setTimestamp(sqlRowser.getString ("timestamp"));
            mathcedHistoryPojo.setFirstcaller(sqlRowser.getString ("firstcaller"));
            mathcedHistoryPojo.setSeccondcaller(sqlRowser.getString ("seccondcaller"));
            mathcedHistoryPojo.setNum(sqlRowser.getInt("num"));
            list.add (mathcedHistoryPojo);
        }
        return list;
    }


    public List<MathcedHistoryPojo> showHistoryMatchedOrderInfo(int pageNum, int pageRow) {
        list = new ArrayList();
        int starter = (pageNum-1)*pageRow;
        String sql = "select a.tokenAaddress,a.price,a.tokenBaddress,a.tokenId,a.num,b.timestamp, b.firstcaller,b.seccondcaller from orderprice a left join (select firstHashOrder,firstcaller,seccondcaller,timestamp from matchedorderinfo) b on a.order_hash = b.firstHashOrder left join(select order_hash,state from orderstate) c on a.order_hash = c.order_hash where c.state =2 order by b.timestamp desc limit ?,?";
        SqlRowSet sqlRowser = jdbcTemplate.queryForRowSet (sql,new Object[]{starter,pageRow});
        while (sqlRowser.next ()) {
            MathcedHistoryPojo mathcedHistoryPojo = new MathcedHistoryPojo();
            mathcedHistoryPojo.setTokenAaddress(sqlRowser.getString ("tokenAaddress"));
            mathcedHistoryPojo.setPrice(sqlRowser.getString ("price"));
            mathcedHistoryPojo.setTokenBaddress(sqlRowser.getString ("tokenBaddress"));
            mathcedHistoryPojo.setTokenId(sqlRowser.getString ("tokenId"));
            mathcedHistoryPojo.setTimestamp(sqlRowser.getString ("timestamp"));
            mathcedHistoryPojo.setFirstcaller(sqlRowser.getString ("firstcaller"));
            mathcedHistoryPojo.setSeccondcaller(sqlRowser.getString ("seccondcaller"));
            mathcedHistoryPojo.setNum(sqlRowser.getInt("num"));
            list.add (mathcedHistoryPojo);
        }
        return list;
    }


    public int countHistoryMatchedOrder(){
        String sql = "select  count(a.tokenId) from orderprice a left join (select firstHashOrder,firstcaller,seccondcaller,timestamp from matchedorderinfo) b on a.order_hash = b.firstHashOrder left join(select order_hash,state from orderstate) c on a.order_hash = c.order_hash where c.state =2 order by b.timestamp desc";
        int count  = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }


    public int countHistoryMatchedOrder(String tokenAddress, String tokenId){
        String sql = "select count(a.tokenId) from orderprice a left join (select firstHashOrder,firstcaller,seccondcaller,timestamp from matchedorderinfo) b on a.order_hash = b.firstHashOrder left join(select order_hash,state from orderstate) c on a.order_hash = c.order_hash   where a.order_hash = b.firstHashOrder and a.tokenBaddress= ? and a.tokenId= ? and c.state =2 order by b.timestamp desc";
        int count  = jdbcTemplate.queryForObject(sql, new Object[]{tokenAddress,tokenId}, Integer.class);
        return count;
    }


    public int counMyProgressingOrder(String callAddress){
        String sql = "select  count( a.order_hash) from orderstate a left join ( select txHash, signature , timesTamp,orderHash, oId, orderData,callData,assetData,order_hash from orderinfo) b on a.order_hash = b.order_hash left join(select order_hash, expirationTime,ListingTime from orderhashinfo) c on a.order_hash = c.order_hash   where a.state = 1 and  unix_timestamp() > c.ListingTime  and unix_timestamp()  < c.expirationTime  and a.callAddress = ? order by oId;";
        int count  = jdbcTemplate.queryForObject(sql, new Object[]{callAddress}, Integer.class);
        return count;
    }

    public int countAll() {
        String sql= "select a.order_hash, b.signature, b.orderHash, b.orderData, b.callData, b.assetData , b.txHash,b.timesTamp from orderstate a left join ( select txHash, signature , timesTamp,orderHash, oId, orderData,callData,assetData,order_hash from orderinfo) b on a.order_hash = b.order_hash left join(select order_hash, expirationTime,ListingTime from orderhashinfo) c on a.order_hash = c.order_hash   where a.state = 1 and unix_timestamp() < c.expirationTime  and  unix_timestamp() > c.ListingTime  order by oId";
        RowCountCallbackHandler countCallback = new RowCountCallbackHandler();
        jdbcTemplate.query(sql,countCallback);
        int count = countCallback.getRowCount();
        return count;
    }

    public  int countSecondaryMarket(){
        String sql= "select a.order_hash, b.signature, b.orderHash, b.orderData, b.callData, b.assetData , b.txHash,b.timesTamp from orderstate a left join ( select txHash, signature , timesTamp,orderHash, oId, orderData,callData,assetData,order_hash from orderinfo) b on a.order_hash = b.order_hash left join(select maker, order_hash, expirationTime,ListingTime from orderhashinfo) c on a.order_hash = c.order_hash   where a.state = 1 and  unix_timestamp() > c.ListingTime  and unix_timestamp()  < c.expirationTime and c.maker != '0x7291030263771b40731d6bc6b352358d23f5737f' order by oId";
        RowCountCallbackHandler countCallback = new RowCountCallbackHandler();
        jdbcTemplate.query(sql,countCallback);
        int count = countCallback.getRowCount();
        return count;
    }
}

