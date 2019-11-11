package com.wondersgroup.dao;

import com.wondersgroup.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author chenlin
 * @create 2019-11-11 15:38
 * @description: TODO
 * @version：1.0
 **/
public interface UserDao extends CrudRepository<User,Long> {

    public List<User> findByName(String name);

    @Query(value=" from User where name = ?1 ")
    public List<User> find(String name);

    @Query(value=" from User where name = :name")
    public List<User> findName(String name);

    @Query("select u from User u where u.name = :#{#user.name} or u.email = :#{#user.email}")
    Iterable<User> findByNameOrEmail(User user);


}
