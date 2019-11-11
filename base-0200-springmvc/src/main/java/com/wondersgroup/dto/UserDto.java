package com.wondersgroup.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenlin
 * @create 2019-05-29 22:13
 * @description: userDto
 * @version：1.0
 **/
@Data
public class UserDto implements Serializable {

    private Long id;
    private String loginName;
    private String password;
}
