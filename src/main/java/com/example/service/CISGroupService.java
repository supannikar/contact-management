package com.example.service;

import com.example.dao.CISGroupDao;
import com.example.model.CISGroupModel;
import com.example.query.CISGroupQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cisGroupService")
public class CISGroupService {

    @Autowired
    private CISGroupDao cisGroupDao;

    public CISGroupModel save(CISGroupModel model){
        return cisGroupDao.save(model);
    }
    public CISGroupModel findById(Long id){
        return cisGroupDao.findById(id);
    }

    public List<CISGroupModel> listAll(){
        return cisGroupDao.listAll();
    }

    public List<CISGroupModel> groupByName(){
        return cisGroupDao.groupByName();
    }

    public List<CISGroupModel> queryCISGroup(CISGroupQuery cisGroupQuery){
        return cisGroupDao.queryCISGroup(cisGroupQuery);
    }

    public int countCISGroup(CISGroupQuery cisGroupQuery){
        return cisGroupDao.countCISGroup(cisGroupQuery);
    }

    public void delete(Integer id){
        cisGroupDao.delete(id);
    }
}
