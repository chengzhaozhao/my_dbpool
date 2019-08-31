package com.czz.dbpool.handler;

import lombok.Data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author chengzhzh@datangmobile.com
 * @create 2019-08-30 22:46
 * 封装 connection
 * 提供 query sql point
 * multiplexer
 * close connect is closed ?
 *
 */
@Data
public class MyPooledConnection {
    private Connection connection = null;
    private boolean isBusy =false;

    public MyPooledConnection(Connection connection, boolean isBusy) {
        this.connection = connection;
        this.isBusy = isBusy;
    }

    public void close(){
        this.isBusy = false;
    }

    public ResultSet query(String sql){
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
