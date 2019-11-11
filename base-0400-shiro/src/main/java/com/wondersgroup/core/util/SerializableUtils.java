package com.wondersgroup.core.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author chenlin
 * @create 2019-06-21 22:14
 * @description: 序列化工具
 * @version：1.0
 **/
public class SerializableUtils {

    /**
     * 方法作用：对象序列化
     *
     * @param object
     * @return: java.lang.String
     * @createDate: 2019/6/21 22:07
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 22:07
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static String serialize(Object object) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            return Base64.encodeToString(bos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("serialize session error", e);
        }
    }

    /**
     * 方法作用： 对象反序列化
     *
     * @param sessionStr
     * @return: java.lang.Object
     * @createDate: 2019/6/21 22:16
     * @createAuthor: chenlin
     * @updateDate: 2019/6/21 22:16
     * @updateAuthor: 修改作者
     * @updateRemark: 修改内容
     **/
    public static Object deserialize(String sessionStr) {

        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(sessionStr));
            ObjectInputStream ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("deserialize session error", e);
        }
    }
}
