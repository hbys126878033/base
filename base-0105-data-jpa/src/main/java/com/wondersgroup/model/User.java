package com.wondersgroup.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chenlin
 * @create 2019-11-11 9:41
 * @description: TODO
 * @version：1.0
 **/
@Data
@Entity
@Table(name="user")
public class User {

    @Id
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
