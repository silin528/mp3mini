package com.silin.mongdb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Create by silin
 * Date:  2019/6/28 23:59
 */
@Data
public class Info {

    @Id
    private String sex;

    private String addres;

    private String telphone;

    private Info(){

    }
    public Info(String sex, String addres, String telphone){
        this.sex = sex;
        this.addres = addres;
        this.telphone = telphone;

    }

    @Override
    public String toString(){
        return "Info["+"sex='"+sex+'\''+",addres='" + addres+'\''+",telphone='" + telphone+'\''+'}';
    }
}
