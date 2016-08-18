package com.example.web.api.v1.mapper;

import com.example.model.CISDetailModel;
import com.example.web.api.v1.transport.CISDetailTransport;
import com.example.web.api.v1.transport.CISGroupTransport;
import com.example.web.api.v1.util.DateTimeFormatUtil;

import java.util.List;
import java.util.stream.Collectors;

public class CISDetailResponseTransportMapper {

    public CISDetailTransport map(CISDetailModel model){
        CISDetailTransport transport = new CISDetailTransport();
        transport.setId(model.getId());
        transport.setName(model.getName());
        transport.setEmail(model.getEmail());
        transport.setPhone(model.getPhone());
        transport.setGroupId(model.getGroupId());
//        transport.setModifiedTime(model.getModifiedTime() != null ?
//                DateTimeFormatUtil.dateToISO8601String(model.getModifiedTime().toDate()) : null);
        return transport;
    }

    public List<CISDetailTransport> maps(List<CISDetailModel> models){
        return models.stream().map(this::map).collect(Collectors.toList());
    }
}
