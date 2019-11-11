package com.wondersgroup.dao;

import com.wondersgroup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chenlin
 * @create 2019-11-11 16:06
 * @description: TODO
 * @version：1.0
 **/
public interface UserJapDap extends JpaRepository<User,Long> {

}
