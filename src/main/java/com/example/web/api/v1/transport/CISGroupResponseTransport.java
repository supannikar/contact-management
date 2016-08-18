package com.example.web.api.v1.transport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CISGroupResponseTransport {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("detail_no")
    private Long detailNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDetailNo() {
        return detailNo;
    }

    public void setDetailNo(Long detailNo) {
        this.detailNo = detailNo;
    }
}
