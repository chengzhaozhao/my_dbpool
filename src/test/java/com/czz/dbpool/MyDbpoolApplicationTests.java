package com.czz.dbpool;

import com.czz.dbpool.factory.MyPoolFactory;
import com.czz.dbpool.handler.MyPooledConnection;
import com.czz.dbpool.service.IMyPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyDbpoolApplicationTests {
    public static IMyPool myPool = MyPoolFactory.getInstance();



    @Test
    public void contextLoads() {
        try {
            for (int i = 0; i < 1000; i++) {
                MyPooledConnection myPooledConnection = myPool.getMyPooledConnection();
                ResultSet query = myPooledConnection.query("select *  from tb_user");
                while (query.next()){
                    System.out.println(query.getString("name")+","+query.getString("address")+",使用管道"+myPooledConnection.getConnection());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
