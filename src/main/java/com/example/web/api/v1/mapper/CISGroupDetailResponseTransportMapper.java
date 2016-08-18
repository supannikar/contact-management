package com.example.web.api.v1.mapper;

import com.example.model.CISGroupModel;
import com.example.web.api.v1.transport.CISGroupResponseTransport;

import java.util.List;
import java.util.stream.Collectors;

public class CISGroupDetailResponseTransportMapper {

    public CISGroupResponseTransport map(CISGroupModel model){
        CISGroupResponseTransport transport = new CISGroupResponseTransport();
        transport.setId(model.getId());
        transport.setName(model.getName());
        transport.setDetailNo(model.getClickCount());
        return transport;
    }

    public List<CISGroupResponseTransport> maps(List<CISGroupModel> models){
        return models.stream().map(this::map).collect(Collectors.toList());
    }
}
