package com.wondersgroup.shiro.listener;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenlin
 * @create 2019-06-21 8:36
 * @description: 由于使用了shiro，容器的会话将由shiro管理，
 * 该自定义的会话监听器，监听会话的生命周期，启动，停止和过期
 * 如果只想监听三个方法中特定的方法，也可以继承 {@link org.apache.shiro.session.SessionListenerAdapter}
 * <p>
 * 最终需要把该监听器配置到SessionManager的对象中，
 * 注意，这个SessionManager只能shiro自己实现的会话管理的，不能是servlet容器管理的
 * @version：1.0
 **/
public class CustomSessionListener implements SessionListener {


    private static final Logger logger = LoggerFactory.getLogger(CustomSessionListener.class);

    @Override
    public void onStart(Session session) {
        System.out.println(session.getId() + " created ," + session.toString());
        logger.info("session create ,id={},host={},startTimestamp={},lastAccessTime={},timeout={} 秒", session.getId(), session.getHost(), session.getStartTimestamp(), session.getLastAccessTime(), session.getTimeout() / 1000);
    }

    @Override
    public void onStop(Session session) {
        System.out.println(session.getId() + " stop ," + session.toString());
        logger.info("session stop ,id={},host={},startTimestamp={},lastAccessTime={},timeout={} 秒", session.getId(), session.getHost(), session.getStartTimestamp(), session.getLastAccessTime(), session.getTimeout() / 1000);

    }

    @Override
    public void onExpiration(Session session) {
        logger.info("session expire ,id={},host={},startTimestamp={},lastAccessTime={},timeout={} 秒", session.getId(), session.getHost(), session.getStartTimestamp(), session.getLastAccessTime(), session.getTimeout() / 1000);

    }
}
