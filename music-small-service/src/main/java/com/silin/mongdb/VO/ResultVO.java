package com.silin.mongdb.VO;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by silin
 * Date:  2020/3/7 22:04
 */
@Getter
@Setter
public class ResultVO<T> {

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;


}
