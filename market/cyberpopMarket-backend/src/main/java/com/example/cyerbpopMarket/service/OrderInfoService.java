package com.example.cyerbpopMarket.service;

import com.example.cyerbpopMarket.algorithm.MinimumNum;
import com.example.cyerbpopMarket.dao.OrderDao;
import com.example.cyerbpopMarket.page.PageList;
import com.example.cyerbpopMarket.page.PageOrder;
import com.example.cyerbpopMarket.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInfoService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PageOrder pageOrder;

    @Autowired
    private MinimumNum minimumNum;


    public boolean insertOrderdataService(OrderInfoPojo orderInfoPojo) {
        orderDao.insertOrderdata(orderInfoPojo.getTxHash(),orderInfoPojo.getOrderHash(),orderInfoPojo.getAssetData(),orderInfoPojo.getOrderData(),
                orderInfoPojo.getCallData(),orderInfoPojo.getSignature(),orderInfoPojo.getTimesTamp(),orderInfoPojo.getOrder_hash());
        return true;
    }

    public  boolean insertOrderState(String callAddress,String order_hash, int state){
        return  orderDao.insertOrderState(callAddress,order_hash,state);
    }

    public  boolean updateOrderState(String order_hash, int state){
        return  orderDao.updateOrderState(order_hash,state);
    }


    public boolean insertOrderCancelData(CancelOrderPojo cancelOrderPojo ){
        return  orderDao.insertOrderCancelData(cancelOrderPojo.getOrder_hash(), cancelOrderPojo.getTxHash(),cancelOrderPojo.getTimestamp(),cancelOrderPojo.getCalladdress());
    }

    public  boolean insertOrderMatchedData(OrderMatchPojo orderMatchPojo){
        return orderDao.insertOrderMatchedData(orderMatchPojo.getFirstCaller(),orderMatchPojo.getSeccondCaller(),orderMatchPojo.getFirstHashOrder(),orderMatchPojo.getSeccondHashOrder(),orderMatchPojo.getTimestamp());
    }

    public boolean insertOrderHashInfo(OrderHashInfoPojo orderHashInfoPojo){
        return  orderDao.insertOrderHashInfo(orderHashInfoPojo.getOrderHash(),orderHashInfoPojo.getMaker(),orderHashInfoPojo.getSalt(),Long.parseLong(orderHashInfoPojo.getListingTime()), Long.parseLong(orderHashInfoPojo.getExpirationTime()),orderHashInfoPojo.getOffer());
    }

    public  boolean insertOrderPrice(String orderHash,OrderDataPojo orderDataPojo,AssetPojo assetPojo){
        return  orderDao.insertOrderPrice(orderHash,assetPojo.getTokenAddress(),assetPojo.getNum(),orderDataPojo.getTokenAddress(),orderDataPojo.getId(),orderDataPojo.getNum());
    }

    public PageList showAllOrderInfo(int pageNum, int pageRow) {
        PageList pageList = new PageList();
        if(pageNum == 0){pageNum=1;}
        if(pageRow == 0){pageNum=30;}
        List list= pageOrder.findAllbyPage(pageNum, pageRow );
        int TotalRows = pageOrder.countAll();
        pageList.setPage(pageNum);
        pageList.setTotalRows(TotalRows);
        int pages= 0;
        if(TotalRows % pageRow == 0){ pages = TotalRows / pageRow;}
        else { pages = TotalRows / pageRow +1 ;}
        pageList.setPages(pages);
        pageList.setList(list);
        return pageList;
    }

    public PageList showAllOrderInfo2(int pageNum, int pageRow) {
        PageList pageList = new PageList();
        if(pageNum == 0){pageNum=1;}
        if(pageRow == 0){pageNum=30;}
        List list= pageOrder.showAllProgressingOrderInfo2(pageNum, pageRow );
        int TotalRows = pageOrder.countAll();
        pageList.setPage(pageNum);
        pageList.setTotalRows(TotalRows);
        int pages= 0;
        if(TotalRows % pageRow == 0){ pages = TotalRows / pageRow;}
        else { pages = TotalRows / pageRow +1 ;}
        pageList.setPages(pages);
        pageList.setList(list);
        return pageList;
    }


    public PageList showMyProgressingOrderInfo(int pageNum, int pageRow, String accountsAddress) {
        PageList pageList = new PageList();
        if(pageNum == 0){pageNum=1;}
        if(pageRow == 0){pageNum=30;}
        List list= pageOrder.showMyProgressingOrderInfo(pageNum, pageRow, accountsAddress );
        int TotalRows = pageOrder.counMyProgressingOrder(accountsAddress);
        pageList.setPage(pageNum);
        pageList.setTotalRows(TotalRows);
        int pages= 0;
        if(TotalRows % pageRow == 0){ pages = TotalRows / pageRow;}
        else { pages = TotalRows / pageRow +1 ;}
        pageList.setPages(pages);
        pageList.setList(list);
        return pageList;
    }

    public PageList showSecondaryMarketOrderInfo(int pageNum, int pageRow) {
        PageList pageList = new PageList();
        if(pageNum == 0){pageNum=1;}
        if(pageRow == 0){pageNum=30;}
        List list= pageOrder.showSecondaryMarketOrderInfo(pageNum, pageRow);
        int TotalRows = pageOrder.countSecondaryMarket();
        pageList.setPage(pageNum);
        pageList.setTotalRows(TotalRows);
        int pages= 0;
        if(TotalRows % pageRow == 0){ pages = TotalRows / pageRow;}
        else { pages = TotalRows / pageRow +1 ;}
        pageList.setPages(pages);
        pageList.setList(list);
        return pageList;
    }

    public PageList showHistoryMatchedOrder(int pageNum, int pageRow,String tokenAddress, String tokenId) {
        PageList pageList = new PageList();
        if(pageNum == 0){pageNum=1;}
        if(pageRow == 0){pageNum=30;}
        List list= pageOrder.showHistoryMatchedOrderInfo(pageNum, pageRow,tokenAddress,tokenId);
        int TotalRows = pageOrder.countHistoryMatchedOrder(tokenAddress,tokenId);
        pageList.setPage(pageNum);
        pageList.setTotalRows(TotalRows);
        int pages= 0;
        if(TotalRows % pageRow == 0){ pages = TotalRows / pageRow;}
        else { pages = TotalRows / pageRow +1 ;}
        pageList.setPages(pages);
        pageList.setList(list);
        return pageList;
    }

    public PageList showHistoryMatchedOrder(int pageNum, int pageRow) {
        PageList pageList = new PageList();
        if(pageNum == 0){pageNum=1;}
        if(pageRow == 0){pageNum=30;}
        List list= pageOrder.showHistoryMatchedOrderInfo(pageNum, pageRow);
        int TotalRows = pageOrder.countHistoryMatchedOrder();
        pageList.setPage(pageNum);
        pageList.setTotalRows(TotalRows);
        int pages= 0;
        if(TotalRows % pageRow == 0){ pages = TotalRows / pageRow;}
        else { pages = TotalRows / pageRow +1 ;}
        pageList.setPages(pages);
        pageList.setList(list);
        return pageList;
    }


    public AssetPojo showFloorPriceForErc1155(String tokenAddress,String tokenbAddress,String tokenId,Long timestamp){
        AssetPojo assetPojo = null;
        List<AssetPojo> list = orderDao.showFloorPriceForErc1155(tokenAddress,tokenbAddress,tokenId,timestamp);
        try{
            assetPojo = minimumNum.getFloorPrice(list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return assetPojo;
    }

    public AssetPojo showFloorPriceForErc721(String tokenaAddress,String tokenbAddress,String tokenId,Long timestamp){
        AssetPojo assetPojo = null;
        List<AssetPojo> list = orderDao.showFloorPriceForErc721(tokenaAddress,tokenbAddress,tokenId,timestamp);
        try{
            assetPojo = minimumNum.getFloorPrice(list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return assetPojo;
    }

    public String getTotalSale(String tokenAddress,Long timestamp){
        return orderDao.getTotalSale(tokenAddress,timestamp);
    }

    public String getTotalSaleForErc721(Long timestamp){return  orderDao.getTotalSaleForErc721(timestamp);}

    public String getTotalSaleForErc1155(Long timestamp){return  orderDao.getTotalSaleForErc1155(timestamp);}

}
