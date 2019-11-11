package com.wondersgroup.shiro.session;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.shiro.session.mgt.SimpleSession;

/**
 * @author chenlin
 * @create 2019-06-21 14:59
 * @description: 自定义shiro的session，
 * @version：1.0
 **/
@Data
public class CustomSession extends SimpleSession {

    /* 用户ID */
    private Long userId;
    /* 账号 */
    private String loginName;
    /*客户端IP*/
    private String host;
    /*客户端浏览器*/
    private String browser;
    /*客户端操作系统*/
    private String os;


}
