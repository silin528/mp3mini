package com.silin.mongdb.entity;

import lombok.Data;

import java.util.Date;

/**
 * Create by silin
 * Date:  2020/3/28 10:01
 */

@Data
public class User {

    private Long id;
    private String username;
    private String openid;
    private Date createTime;
    private Date updateTime;
}
