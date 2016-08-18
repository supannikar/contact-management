package com.example.web.api.v1.transport;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseTransport<E> {

    @JsonProperty("total")
    private long totalCount;
    @JsonProperty("count")
    private int limit;
    @JsonProperty("result")
    private List<E> data;

    public ResponseTransport(List<E> data) {
        this.data = data;
    }

    public ResponseTransport(long totalCount, int limit, List<E> data) {
        this.totalCount = totalCount;
        this.limit = limit;
        this.data = data;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }
}
