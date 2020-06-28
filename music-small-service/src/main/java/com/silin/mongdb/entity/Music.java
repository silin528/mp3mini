package com.silin.mongdb.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create by silin
 * Date:  2020/3/7 18:22
 */
@Getter
@Setter
public class Music {

    private Long id;
    private Long singer_id;
    private String name;
    private String cover;
    private Integer type;
    private String url;
    private String lrc;
    private Date created;
    private Date date;
    private Singer singer;

}
