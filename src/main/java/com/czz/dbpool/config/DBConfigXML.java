package com.czz.dbpool.config;

import lombok.experimental.UtilityClass;

/**
 * @author chengzhzh@datangmobile.com
 * @create 2019-08-30 22:46
 * xml or properties
 */
@UtilityClass
public class DBConfigXML {
    public  final String CONNECTIONURL = "jdbc:mysql://39.98.200.95:3306/czz_java";
    public  final String Driver = "com.mysql.jdbc.Driver";
    public  final String USERNAME = "root";
    public  final String PASSWORD = "Shikp@2019";


    public  final int initCount = 10;// 数据库连接池初始值大小
    public  final int step = 2;// 连接池不足时增长的步长
    public  final int maxCount = 50;// 连接池的最大数量
}
