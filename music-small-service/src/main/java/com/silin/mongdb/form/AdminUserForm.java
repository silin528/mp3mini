package com.silin.mongdb.form;

import lombok.Data;

/**
 * Create by silin
 * Date:  2020/3/28 17:08
 */
@Data
public class AdminUserForm {
    private String username;
    private String password;
    private String phone;
    private Long id;
}
