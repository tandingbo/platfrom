package com.tanbobo.platfrom.base.common.session.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;

/**
 * Created by tanbobo on 2016/6/30.
 */
public class ZkSessionAttributeDataListener implements IZkDataListener {
    @Override
    public void handleDataChange(String arg0, Object arg1) throws Exception {
        String name = arg0.substring(arg0.lastIndexOf("/") + 1);
        Object value = arg1;
        String prefix = arg0.substring(0, arg0.lastIndexOf("/"));
        String sid = prefix.substring(prefix.lastIndexOf("/") + 1);
        ((ZkSession) ZkSessionManager.getInstance().getSession(sid)).localSetAttribute(name, value);

    }

    @Override
    public void handleDataDeleted(String arg0) throws Exception {

    }
}
