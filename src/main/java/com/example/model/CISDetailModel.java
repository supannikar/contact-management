package com.example.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class CISDetailModel {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Long groupId;
//    private DateTime modifiedTime;
}
