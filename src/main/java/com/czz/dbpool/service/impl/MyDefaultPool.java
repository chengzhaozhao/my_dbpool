package com.czz.dbpool.service.impl;

import com.czz.dbpool.config.DBConfigXML;
import com.czz.dbpool.handler.MyPooledConnection;
import com.czz.dbpool.service.IMyPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author chengzhzh@datangmobile.com
 * @create 2019-08-30 22:45
 */
public class MyDefaultPool implements IMyPool {

   private Vector<MyPooledConnection> myPooledConnectionVector = new Vector<>();
   private String jdbcUrl;
   private String jdbcUserName;
   private String jdbcPassword;
   private int initCount;
   private int step;
   private int maxCount;



    public MyDefaultPool() {
        //初始化数据连接池
        init();
        // 加载驱动
        try {
            Class.forName(DBConfigXML.Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 初始化数据库连接池管道
        createMyPooledConnection(initCount);
    }

    private void init() {
        jdbcUrl = DBConfigXML.CONNECTIONURL;
        jdbcUserName = DBConfigXML.USERNAME;
        jdbcPassword =DBConfigXML.PASSWORD;
        initCount = DBConfigXML.initCount;
        step = DBConfigXML.step;
        maxCount =DBConfigXML.maxCount;
    }

    @Override
    public MyPooledConnection getMyPooledConnection() {
        if(myPooledConnectionVector.size()<1){
            throw new RuntimeException("连接池初始化错误");
        }
        MyPooledConnection myPooledConnection = null;

        myPooledConnection = getRealConnectionFromPool();

        while (myPooledConnection == null) {
            createMyPooledConnection(step);

            myPooledConnection = getRealConnectionFromPool();

            return myPooledConnection;
        }
        return myPooledConnection;
    }

    private MyPooledConnection getRealConnectionFromPool() {
        try {
            for (MyPooledConnection myPooledConnection : myPooledConnectionVector) {
                if (!myPooledConnection.isBusy()) {
                        if (myPooledConnection.getConnection().isValid(3000)) {
                            myPooledConnection.setBusy(true);
                            return myPooledConnection;
                        }

                }else{
                    Connection connection = null;
                        connection = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword);
                        myPooledConnection.setConnection(connection);
                        myPooledConnection.setBusy(true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createMyPooledConnection(int count) {
        if(myPooledConnectionVector.size()>maxCount || myPooledConnectionVector.size() + count > maxCount){
            throw new  RuntimeException("连接池已满");
        }
        for (int i = 0; i < count; i++) {
            try {
                Connection connection = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword);
                MyPooledConnection myPooledConnection = new MyPooledConnection(connection,false);
                myPooledConnectionVector.add(myPooledConnection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }



}
