package com.silin.mongdb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.Date;
import java.util.List;

/**
 * Create by silin
 * Date:  2019/6/27 23:55
 */
@Data
public class AdminUser {


    private Long id;

    private String username;

    private String password;

    private String phone;

    private Date createTime;

    private Date updateTime;

}
