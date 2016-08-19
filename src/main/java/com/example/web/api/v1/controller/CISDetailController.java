package com.example.web.api.v1.controller;

import com.example.model.CISDetailModel;
import com.example.query.CISDetailQuery;
import com.example.service.CISDetailService;
import com.example.web.api.v1.mapper.CISDetailRequestTransportMapper;
import com.example.web.api.v1.mapper.CISDetailResponseTransportMapper;
import com.example.web.api.v1.transport.CISDetailTransport;
import com.example.web.api.v1.transport.CISGroupTransport;
import com.example.web.api.v1.transport.ResponseTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cis/v1/cisdetails", produces = MediaType.APPLICATION_JSON_VALUE)
public class CISDetailController {
    private static final Logger LOG = LoggerFactory.getLogger(CISDetailController.class);
    private static final String DEFAULT_RESPONSE_LIMIT = "50";
    private static final String DEFAULT_OFFSET = "0";
    @Autowired
    private CISDetailService cisDetailService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CISDetailTransport create(@RequestBody @Valid CISDetailTransport cisDetailTransport) {

        CISDetailModel cisDetailModel = new CISDetailRequestTransportMapper().map(cisDetailTransport);
        CISDetailModel createDetail = cisDetailService.save(cisDetailModel);

        LOG.info("New group is created with generated id {}", createDetail.getId());
        return new CISDetailResponseTransportMapper().map(createDetail);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CISDetailTransport update(@RequestBody @Valid CISDetailTransport cisDetailTransport, @PathVariable Long id) {

        CISDetailModel model = new CISDetailRequestTransportMapper().map(cisDetailTransport);
        CISDetailModel updateDetail = cisDetailService.save(model);

        LOG.info("New group is created with generated id {}", updateDetail.getId());
        return new CISDetailResponseTransportMapper().map(updateDetail);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CISDetailTransport findById(@PathVariable Long id) {
        return new CISDetailResponseTransportMapper().map(cisDetailService.findById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseTransport<CISDetailTransport> listAll(@RequestParam(value = "name", required = false) String name,
                                                         @RequestParam(value = "email", required = false) String email,
                                                         @RequestParam(value = "phone", required = false) String phone,
                                                         @RequestParam(value = "sortby", defaultValue = "name") String sortBy,
                                                         @RequestParam(value = "limit", defaultValue = DEFAULT_RESPONSE_LIMIT)  Integer limit,
                                                         @RequestParam(value = "offset", defaultValue = DEFAULT_OFFSET) Integer offset) {

        CISDetailQuery cisDetailQuery = new CISDetailQuery.Builder().name(name)
                .email(email)
                .phone(phone)
                .sortBy(getSortField(sortBy))
                .limit(limit)
                .offset(offset)
                .build();
        List<CISDetailModel> listAll = cisDetailService.queryCISDetail(cisDetailQuery);
        int total = cisDetailService.countCISDetail(cisDetailQuery);
        List<CISDetailTransport> transports = new CISDetailResponseTransportMapper().maps(listAll);
        return new ResponseTransport<>(total, transports.size(), transports);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable Integer id) {
        cisDetailService.delete(id);
        return Response.status(Response.Status.OK).build();
    }

    private CISDetailQuery.SortGroupName getSortField(String field) {
        switch (field.toLowerCase()) {
            case "name" : return CISDetailQuery.SortGroupName.GROUP_NAME;
            default : return  null;
        }
    }
}
