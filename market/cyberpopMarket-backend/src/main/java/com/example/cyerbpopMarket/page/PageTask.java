//package com.example.cyerbpopMarket.page;
//
//
//import com.example.cyerbpopMarket.pojo.OrderInfoPojo;
//import com.nem.bounty.pojo.BaseTaskPojo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowCountCallbackHandler;
//import org.springframework.jdbc.support.rowset.SqlRowSet;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Repository
//public class PageTask {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//
//    @Autowired
//    private OrderInfoPojo orderInfoPojo;
//
//    private List list;
//
//    public List<OrderInfoPojo> findAll() {
//        list = new ArrayList();
//        String sql = "select a.taskId,a.title,a.prize,a.totalNum,a.singlePrize,a.status,a.walletAddress, IFNULL(submitNum,0) as submitNum from task a left join (select COUNT(audit) as submitNum, taskId from clienttask  group by taskId) b on a.taskId = b.taskId";
//        SqlRowSet sqlRowser = jdbcTemplate.queryForRowSet (sql);
//        while (sqlRowser.next ()) {
//            baseTaskPojo = new BaseTaskPojo();
//            baseTaskPojo.setSinglePrize(sqlRowser.getInt ("singlePrize"));
//            baseTaskPojo.setTotalNum(sqlRowser.getInt ("totalNum"));
//            baseTaskPojo.setStatus(sqlRowser.getInt ("status"));
//            baseTaskPojo.setTaskId(sqlRowser.getInt ("taskId"));
//            baseTaskPojo.setSubmitNum(sqlRowser.getInt ("submitNum"));
//            baseTaskPojo.setWalletAddress(sqlRowser.getString ("walletAddress"));
//            baseTaskPojo.setTitle(sqlRowser.getString ("title"));
//            list.add (baseTaskPojo);
//        }
//        return list;
//    }
//
//    public List<OrderInfoPojo> findAllbyPage(int pageNum, int pageRow) {
//        list = new ArrayList();
//        int starter = (pageNum-1)*pageRow;
//        String sql = "select a.taskId,a.title,a.prize,a.totalNum,a.singlePrize,a.status,a.walletAddress, IFNULL(submitNum,0) as submitNum from task a left join (select COUNT(audit) as submitNum, taskId from clienttask  group by taskId) b on a.taskId = b.taskId order by taskId asc  limit ? ,?";
//        SqlRowSet sqlRowser = jdbcTemplate.queryForRowSet (sql,new Object[]{starter,pageRow});
//        while (sqlRowser.next ()) {
//            baseTaskPojo = new BaseTaskPojo();
//            baseTaskPojo.setTaskId(sqlRowser.getInt ("taskId"));
//            baseTaskPojo.setSinglePrize(sqlRowser.getInt ("singlePrize"));
//            baseTaskPojo.setTotalNum(sqlRowser.getInt ("totalNum"));
//            baseTaskPojo.setStatus(sqlRowser.getInt ("status"));
//            baseTaskPojo.setTaskId(sqlRowser.getInt ("taskId"));
//            baseTaskPojo.setSubmitNum(sqlRowser.getInt ("submitNum"));
//            baseTaskPojo.setWalletAddress(sqlRowser.getString ("walletAddress"));
//            baseTaskPojo.setTitle(sqlRowser.getString ("title"));
//            list.add (baseTaskPojo);
//        }
//        return list;
//    }
//
//    public int countAll() {
//        String sql= "select taskId from task";
//        RowCountCallbackHandler countCallback = new RowCountCallbackHandler();
//        jdbcTemplate.query(sql,countCallback);
//        int count = countCallback.getRowCount();
//        return count;
//    }
//}
