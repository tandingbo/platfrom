package com.tanbobo.platfrom.base.common.parallel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 测试类
 */
public class BXTest {
    /**
     * 示例（hello 示例）
     */
    @Test
    public void HelloTest() {
        List<String> paramCollection = new ArrayList<String>();
        paramCollection.add("9");
        paramCollection.add("2");
        paramCollection.add("18");
        paramCollection.add("7");
        paramCollection.add("6");
        paramCollection.add("1");
        paramCollection.add("3");
        paramCollection.add("4");
        paramCollection.add("14");
        paramCollection.add("13");

        int freesize = 4;//当前处理能力

        for (int i = 0; i < paramCollection.size(); i = i + freesize) {

            List<String> tl = BXexample.getSubListPage(paramCollection, i, freesize);
            System.out.println(tl);

            BXexample.BXfunction(tl, executor -> {
                int k = Integer.parseInt((String) executor);
                System.out.println(k);
                for (int i1 = 0; i1 < k * 10000000; i1++) {
                    //执行循环
                }
                System.out.println(k + "：hello world");
            });
        }
    }

    /**
     * 实际业务应用示例
     */
    public void ActualTest() {
        /**
         * 并行调度相关处理
         *
         * 按卫星*日期 ，将待处理的任务分解为 卫星+日期 粒度的子任务 添加到paramMapList列表中
         */
//        List<Map<String, Object>> paramMapList = new ArrayList<Map<String, Object>>();
//        for (Iterator<OrderParamSatellite> iterator = paramSatellites.iterator(); iterator.hasNext();) {
//            OrderParamSatellite paramSatellite = iterator.next();
//
//            paramMapList.addAll(this.getParamMapList(paramSatellite));
//        }


        //根据集群最大处理能力,分页处理任务列表，作为list截取的步长

//        int fsize = HostServerQueue.getInstance().freeSize();
//        for(int i=0;i<paramMapList.size();i=i+fsize){
//            List<Map<String, Object>> tl = BXexample.getSubListPage(paramMapList, i,  fsize);
        //并行调度
//            BXexample.BXfunction(tl,new ExectueCallBack(){
//                public void doExectue(Object executor) throws Exception {
//                    ExecuteOrderBTask((Map<String, Object>)executor);
//                }
//            });

        //动态查找空闲节点数量，即集群最大处理能力
//            fsize = HostServerQueue.getInstance().freeSize();
//        }
    }
}
