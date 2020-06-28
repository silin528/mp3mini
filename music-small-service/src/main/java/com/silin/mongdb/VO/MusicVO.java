package com.silin.mongdb.VO;

import com.silin.mongdb.entity.Singer;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Create by silin
 * Date:  2020/3/23 22:06
 */
@Getter
@Setter
public class MusicVO {

    private String id;
    private Long singer_id;
    private String name;
    private String cover;
    private Integer type;
    private String url;
    private String lrc;
    private Long created;
    private Long date;
    private Singer singer;

}
