package com.tanbobo.platfrom.base.common.session.zookeeper;

import com.tanbobo.platfrom.base.common.session.SessionMetaData;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created by tanbobo on 2016/6/30.
 */
public class ZkSessionDataListener implements IZkDataListener {
    @Override
    public void handleDataChange(String arg0, Object arg1) throws Exception {
        ZkClient client = ZkConnectionSingleton.getInstance();
        String sid = arg0.substring(arg0.lastIndexOf("/") + 1);
        ZkSession session = (ZkSession) ZkSessionManager.getInstance().getSession(sid);
        SessionMetaData meta = client.readData(arg0);
        session.setMeta(meta);
    }

    @Override
    public void handleDataDeleted(String arg0) throws Exception {

    }
}
