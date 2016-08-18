package com.example.service;

import com.example.dao.CISDetailDao;
import com.example.model.CISDetailModel;
import com.example.query.CISDetailQuery;
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

    public List<CISDetailModel> queryCISDetail(CISDetailQuery cisDetailQuery){
        return cisDetailDao.queryCISDetail(cisDetailQuery);
    }

    public int countCISDetail(CISDetailQuery cisDetailQuery){
        return cisDetailDao.countAdObject(cisDetailQuery);
    }

    public void delete(Integer id){
        cisDetailDao.delete(id);
    }
}
