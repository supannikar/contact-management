package com.example.web.api.v1.transport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CISGroupTransport {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

//    @JsonProperty("modified_time")
//    private String modifiedTime;

    @JsonProperty("click_count")
    private Long clickCount = 0L;

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

//    public String getModifiedTime() {
//        return modifiedTime;
//    }
//
//    public void setModifiedTime(String modifiedTime) {
//        this.modifiedTime = modifiedTime;
//    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }
}
