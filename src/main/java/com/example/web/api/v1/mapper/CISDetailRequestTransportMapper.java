package com.example.web.api.v1.mapper;

import com.example.model.CISDetailModel;
import com.example.model.CISGroupModel;
import com.example.web.api.v1.transport.CISDetailTransport;
import com.example.web.api.v1.transport.CISGroupTransport;
import com.example.web.api.v1.util.DateTimeFormatUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

public class CISDetailRequestTransportMapper {

    public CISDetailModel map(CISDetailTransport transport){
        CISDetailModel model = new CISDetailModel();
        model.setId(transport.getId());
        model.setName(transport.getName());
        model.setEmail(transport.getEmail());
        model.setPhone(transport.getPhone());
        model.setGroupId(transport.getGroupId());

//        if(StringUtils.isNotBlank(transport.getModifiedTime())) {
//            model.setModifiedTime(DateTimeFormatUtil.fromISO8601String(transport.getModifiedTime()));
//        } else {
//            model.setModifiedTime(DateTime.now());
//        }

        return model;
    }
}
