package com.example.service;

import com.example.dao.CISDetailDao;
import com.example.model.CISDetailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cisDetailService")
public class CISDetailService {

    @Autowired
    private CISDetailDao cisDetailDao;

    public CISDetailModel save(CISDetailModel model){
        return cisDetailDao.save(model);
    }
    public CISDetailModel findById(Long id){
        return cisDetailDao.findById(id);
    }

    public List<CISDetailModel> listAll(){
        return cisDetailDao.listAll();
    }

    public void delete(Integer id){
        cisDetailDao.delete(id);
    }
}
