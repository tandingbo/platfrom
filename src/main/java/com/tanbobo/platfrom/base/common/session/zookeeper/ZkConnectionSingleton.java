package com.tanbobo.platfrom.base.common.session.zookeeper;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created by tanbobo on 2016/6/30.
 */
public class ZkConnectionSingleton {
    private static String zkServer = "123.56.126.226:2182";
    private static ZkClient zkClient = new ZkClient(zkServer);

    public static ZkClient getInstance() {
        return zkClient;
    }
}
