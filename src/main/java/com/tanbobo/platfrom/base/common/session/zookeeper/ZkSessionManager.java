package com.tanbobo.platfrom.base.common.session.zookeeper;

import com.tanbobo.platfrom.base.common.session.AbstractSessionManager;
import com.tanbobo.platfrom.base.common.session.SessionMetaData;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * Created by tanbobo on 2016/6/30.
 */
public class ZkSessionManager extends AbstractSessionManager {
    private static AbstractSessionManager instance = new ZkSessionManager();

    @Override
    public void loadSession() {
        ZkClient client = ZkConnectionSingleton.getInstance();
        List<String> sessionList = client.getChildren(ZkSessionHelper.root);
        for (int i = 0, len = sessionList.size(); i < len; i++) {
            String sid = sessionList.get(i);
            SessionMetaData meta = client.readData(ZkSessionHelper.root + "/" + sid);
            ZkSession session = new ZkSession();
            session.setId(sid);
            session.setMeta(meta);
            List<String> attributeList = client.getChildren(ZkSessionHelper.root + "/" + sid);
            for (int j = 0, size = attributeList.size(); j < size; j++) {
                String name = attributeList.get(j);
                Object value = client.readData(ZkSessionHelper.root + "/" + sid + "/" + name);
                session.localSetAttribute(name, value);
            }
            AbstractSessionManager sessionManager = ZkSessionManager.getInstance();
            sessionManager.addSession(session, sid);
        }
    }

    public static AbstractSessionManager getInstance() {
        return instance;
    }

}
