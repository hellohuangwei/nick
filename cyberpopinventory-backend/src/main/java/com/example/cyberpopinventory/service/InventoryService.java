package com.example.cyberpopinventory.service;

import com.example.cyberpopinventory.dao.InventoryDao;
import com.example.cyberpopinventory.pojo.BasicDispositionPojo;
import com.example.cyberpopinventory.pojo.DispositionPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryDao inventoryDao;


    public Boolean insertInventoryPeroid(int peroid,String startTIme,String endTime){
        inventoryDao.insertInventoryPeroid(peroid,startTIme,endTime);
        return true;
    }

    public Boolean initInsertDispositionData(String contractAddress, int num, String price, int peroid,String id){
        return  inventoryDao.initInsertDispositionData(contractAddress,num,price,peroid,id);
    }

    public Boolean updateNUm(int num_, int peroid, String contractAddress,String id){
        inventoryDao.updateNUm(num_,peroid,contractAddress,id);
        return true;
    }


    public Boolean resetNUm(int num_, int peroid, String contractAddress,String id){
        inventoryDao.resetNUm(num_,peroid,contractAddress,id);
        return true;
    }



    public List getDispositionData(int peroid){
        return  inventoryDao.getDispositionData(peroid);
    }

    public int getLastPeroid(){
       return inventoryDao.getLastPeroid();
    }

    public BasicDispositionPojo getDispositionData(int peroid, String contractAddress,String id){
        BasicDispositionPojo basicDispositionPojo = new BasicDispositionPojo();
        basicDispositionPojo = inventoryDao.getDispositionData(peroid,contractAddress,id);
        return basicDispositionPojo;
    }

}
