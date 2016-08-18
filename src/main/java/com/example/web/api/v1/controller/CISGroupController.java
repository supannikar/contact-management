package com.example.web.api.v1.controller;

import com.example.model.CISGroupModel;
import com.example.query.CISGroupQuery;
import com.example.service.CISGroupService;
import com.example.web.api.v1.mapper.CISGroupDetailResponseTransportMapper;
import com.example.web.api.v1.mapper.CISGroupRequestTransportMapper;
import com.example.web.api.v1.mapper.CISGroupResponseTransportMapper;
import com.example.web.api.v1.transport.CISGroupResponseTransport;
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
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cis/v1/cisgroups", produces = MediaType.APPLICATION_JSON_VALUE)
public class CISGroupController {
    private static final Logger LOG = LoggerFactory.getLogger(CISGroupController.class);
    private static final String DEFAULT_RESPONSE_LIMIT = "5";
    private static final String DEFAULT_OFFSET = "0";

    @Autowired
    private CISGroupService cisGroupService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CISGroupTransport create(@RequestBody @Valid CISGroupTransport cisGroupTransport) {

        CISGroupModel cisGroupModel = new CISGroupRequestTransportMapper().map(cisGroupTransport);
        CISGroupModel createGroup = cisGroupService.save(cisGroupModel);

        LOG.info("New group is created with generated id {}", createGroup.getId());
        return new CISGroupResponseTransportMapper().map(createGroup);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CISGroupTransport update(@RequestBody @Valid CISGroupTransport cisGroupTransport, @PathVariable Long id) {

        CISGroupModel model = new CISGroupRequestTransportMapper().map(cisGroupTransport);
        CISGroupModel updateGroup = cisGroupService.save(model);

        LOG.info("New group is created with generated id {}", updateGroup.getId());
        return new CISGroupResponseTransportMapper().map(updateGroup);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CISGroupTransport findById(@PathVariable Long id) {
        return new CISGroupResponseTransportMapper().map(cisGroupService.findById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseTransport<CISGroupTransport> listAll(@RequestParam(value = "sortby", defaultValue = "name") String sortBy,
                                                                @RequestParam(value = "limit", defaultValue = DEFAULT_RESPONSE_LIMIT)  Integer limit,
                                                                @RequestParam(value = "offset", defaultValue = DEFAULT_OFFSET) Integer offset) {

        CISGroupQuery cisGroupQuery = new CISGroupQuery.Builder()
                .sortBy(getSortField(sortBy))
                .limit(limit)
                .offset(offset)
                .build();
        List<CISGroupModel> listAll = cisGroupService.queryCISGroup(cisGroupQuery);
        int total = cisGroupService.countCISGroup(cisGroupQuery);
        List<CISGroupTransport> transports = new CISGroupResponseTransportMapper().maps(listAll);
        return new ResponseTransport<>(total, transports.size(), transports);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable Integer id) {
        cisGroupService.delete(id);
        return Response.status(Response.Status.OK).build();
    }

    private CISGroupQuery.SortGroupName getSortField(String field) {
        switch (field.toLowerCase()) {
            case "name" : return CISGroupQuery.SortGroupName.GROUP_NAME;
            default : return  null;
        }
    }
}
