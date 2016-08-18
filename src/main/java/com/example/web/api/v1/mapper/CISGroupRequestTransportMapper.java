package com.example.web.api.v1.mapper;

import com.example.model.CISGroupModel;
import com.example.web.api.v1.transport.CISGroupTransport;
import com.example.web.api.v1.util.DateTimeFormatUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

public class CISGroupRequestTransportMapper {

    public CISGroupModel map(CISGroupTransport transport){
        CISGroupModel model = new CISGroupModel();
        model.setId(transport.getId());
        model.setName(transport.getName());
        model.setClickCount(transport.getClickCount());

        return model;
    }
}
