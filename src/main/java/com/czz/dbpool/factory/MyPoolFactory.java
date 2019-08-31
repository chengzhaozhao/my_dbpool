package com.czz.dbpool.factory;

import com.czz.dbpool.service.IMyPool;
import com.czz.dbpool.service.impl.MyDefaultPool;

/**
 * @author chengzhzh@datangmobile.com
 * @create 2019-08-30 22:46
 */
public class MyPoolFactory {
    public static class createPool{
        public static IMyPool myPool = new MyDefaultPool();
    }

    public static IMyPool getInstance(){
        return createPool.myPool;
    }
}
