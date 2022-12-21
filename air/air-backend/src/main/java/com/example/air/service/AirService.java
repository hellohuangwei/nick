package com.example.air.service;

import com.example.air.dao.AirDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirService {

    @Autowired
    private AirDao airDao;

    public boolean insertAirdata(int peroid,String account,String num) {

        return  airDao.insertAirdata(peroid,account,num);
    }

    public String getAirData(int peroid,String account){
        return  airDao.getUserData(peroid,account);
    }

    public List getAirData(int peroid){
        return  airDao.getUserData(peroid);
    }

    public int getLastPeroid(){
        return  airDao.getLastPeroid();
    }


    public boolean insterRandomData(String root,int peroid,String randomStr){
        airDao.insterRandomData(root,peroid,randomStr);
        return true;
    }

    public String getRewardNumberList(int peroid){
        return  airDao.getRewardNumberList(peroid);
    }
}
