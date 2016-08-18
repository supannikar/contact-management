package com.example.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class CISGroupModel {
    private Long id;
    private String name;
//    private DateTime modifiedTime;
    private Long clickCount;
}
