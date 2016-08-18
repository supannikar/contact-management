package com.example.web.api.v1.mapper;

import com.example.model.CISGroupModel;
import com.example.web.api.v1.transport.CISGroupTransport;
import com.example.web.api.v1.util.DateTimeFormatUtil;

import java.util.List;
import java.util.stream.Collectors;

public class CISGroupResponseTransportMapper {

    public CISGroupTransport map(CISGroupModel model){
        CISGroupTransport transport = new CISGroupTransport();
        transport.setId(model.getId());
        transport.setName(model.getName());
        transport.setClickCount(model.getClickCount());
//        transport.setModifiedTime(model.getModifiedTime() != null ?
//                DateTimeFormatUtil.dateToISO8601String(model.getModifiedTime().toDate()) : null);
        return transport;
    }

    public List<CISGroupTransport> maps(List<CISGroupModel> models){
        return models.stream().map(this::map).collect(Collectors.toList());
    }
}
