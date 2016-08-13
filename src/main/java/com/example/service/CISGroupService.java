package com.example.service;

import com.example.dao.CISGroupDao;
import com.example.model.CISGroupModel;
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

    public void delete(Integer id){
        cisGroupDao.delete(id);
    }
}
