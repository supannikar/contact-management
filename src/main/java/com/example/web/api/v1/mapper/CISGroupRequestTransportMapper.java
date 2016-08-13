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

        if(StringUtils.isNotBlank(transport.getModifiedTime())) {
            model.setModifiedTime(DateTimeFormatUtil.fromISO8601String(transport.getModifiedTime()));
        } else {
            model.setModifiedTime(DateTime.now());
        }

        return model;
    }
}
