package com.wondersgroup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author chenlin
 * @create 2019-11-15 16:57
 * @description: PERSON 实体
 * @version：1.0
 *
 * @EqualsAndHashCode(callSuper = true)
 **/
@Data
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String lastName;
    private String firstName;
}
