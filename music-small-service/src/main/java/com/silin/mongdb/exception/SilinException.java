package com.silin.mongdb.exception;


import com.silin.mongdb.enums.ResultEnum;

public class SilinException extends RuntimeException{

    private Integer code;

    public SilinException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SilinException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
