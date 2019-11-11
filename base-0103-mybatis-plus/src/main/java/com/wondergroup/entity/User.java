package com.wondergroup.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author chenlin
 * @create 2019-11-11 9:41
 * @description: TODO
 * @version：1.0
 **/
@Data
public class User {

    @TableId()
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
