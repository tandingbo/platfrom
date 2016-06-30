package com.tanbobo.platfrom.base.common.session.zookeeper;

import com.tanbobo.platfrom.base.common.session.SessionMetaData;
import org.I0Itec.zkclient.ZkClient;

import java.util.Date;
import java.util.List;

/**
 * Created by tanbobo on 2016/6/30.
 */
public class ZkSessionCleaner extends Thread {
    @Override
    public void run() {
        ZkClient client = ZkConnectionSingleton.getInstance();
        while (true) {
            List<String> sessionList = client.getChildren(ZkSessionHelper.root);
            for (int i = 0, len = sessionList.size(); i < len; i++) {
                String sid = sessionList.get(i);
                SessionMetaData meta = client.readData(ZkSessionHelper.root + "/" + sid);
                ZkSession session = new ZkSession();
                if ((new Date().getTime() - meta.getLastAccessedTime()) > meta.getMaxInactiveInterval()) {

                    client.deleteRecursive(ZkSessionHelper.root + "/" + sid);
                }
            }
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
            }
        }
    }
}
