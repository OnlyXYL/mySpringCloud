package xyl.bmsmart.common.common.util;


import xyl.bmsmart.common.common.model.neo4j.Node;
import xyl.bmsmart.common.common.model.neo4j.Relationship;

import java.util.ArrayList;
import java.util.List;

public class RemoveRepeatDataUtil {
    // 删除ArrayList中重复元素，保持顺序
    public static ArrayList<Node> removeDuplicateUser(List<Node> OldNodes) {

        ArrayList<Node> newNodes = new ArrayList<>();

        OldNodes.stream().forEach(
                p -> {
                    if (!newNodes.contains(p)) {
                        newNodes.add(p);
                    }
                }
        );
        return newNodes;
    }

    /**
     * List<String>去重
     *
     * @param oldList
     * @return java.util.List<java.lang.String>
     * @author XiaYaLing
     * @date 2018/4/19
     */
    public static List<String> removeRepeatString(List<String> oldList) {
        List<String> newList = new ArrayList<String>();
        for (String string : oldList) {
            if (!newList.contains(string)) {
                newList.add(string);
            }
        }
        return newList;
    }

    /**
     * 去除List内复杂字段重复对象
     *
     * @param oldList
     * @return java.util.List<cn.com.bmsmart.J.neo4j.Relationship>
     * @author XiaYaLing
     * @date 2018/4/17
     */
    public static List<Relationship> RemoveRepeatRelationShipList(List<Relationship> oldList) {
        List<Relationship> list = new ArrayList<>();
        if (oldList.size() > 0) {
            for (Relationship relationship : oldList) {
                //list去重复，内部重写equals
                if (!list.contains(relationship)) {
                    list.add(relationship);
                }
            }
        }
        return list;
    }
}
