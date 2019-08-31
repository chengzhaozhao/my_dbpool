package com.czz.dbpool.service;

import com.czz.dbpool.handler.MyPooledConnection;

/**
 * @author chengzhzh@datangmobile.com
 * @create 2019-08-30 22:45
 * dbtool api
 * 获取数据库操作的管道/可以创建数据库的管道
 */
public interface IMyPool {
    public MyPooledConnection getMyPooledConnection();

    public void createMyPooledConnection(int count);
}
