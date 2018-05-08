package xyl.bmsmart.common.common.util;

import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Node;
import xyl.bmsmart.common.common.model.neo4j.Relationship;
import xyl.bmsmart.common.common.model.neo4j.ResultData;
import xyl.bmsmart.common.common.myenum.LabelType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CypherResultUtil {
    /**
     * 处理返回结果
     *
     * @param greeting
     * @return java.lang.String
     * @author XiaYaLing
     * @date 2018/4/18
     */
    public static ResultData dealwithResult(List<Record> greeting) {
        ArrayList<xyl.bmsmart.common.common.model.neo4j.Node> nodes = new ArrayList<>();
        List<Relationship> edges = new ArrayList<>();
        List<String> relationShips = new ArrayList<>();
        ResultData resultData = new ResultData();
        for (int i = 0; i < greeting.size(); i++) {
            //获取每条记录
            Record record = greeting.get(i);
            List<String> keys = record.keys();
            if (keys.size() > 0) {
                //取出所有key,遍历，根据类型进行封装
                for (String key : keys) {
                    Value value = record.get(key);
                    String value_type = value.type().name();
                    if ("NODE".equals(value_type)) {
                        //节点
                        Node node = value.asNode();
                        long id = node.id();
                        xyl.bmsmart.common.common.model.neo4j.Node node1 = new xyl.bmsmart.common.common.model.neo4j.Node();
                        //判断节点标签类型
                        Iterable<String> labels = node.labels();
                        for (Iterator<String> iterator = labels.iterator(); iterator.hasNext(); ) {
                            String next = iterator.next();
                            if (LabelType.Basin.getValue().equals(next)) {
                                //盆地
                                node1.setId(id + "");
                                node1.setNodeName(node.get("name").asString());
                                node1.setLabel(next);
                            } else if (LabelType.First.getValue().equals(next)) {
                                //一级构造
                                node1.setId(id + "");
                                node1.setNodeName(node.get("name").asString());
                                node1.setLabel(next);
                            } else if (LabelType.Second.getValue().equals(next)) {
                                //二级构造
                                node1.setId(id + "");
                                node1.setNodeName(node.get("name").asString());
                                node1.setLabel(next);
                            } else if (LabelType.Third.getValue().equals(next)) {
                                //三级构造
                                node1.setId(id + "");
                                node1.setNodeName(node.get("name").asString());
                                node1.setLabel(next);
                            } else if (LabelType.Part.getValue().equals(next)) {
                                //局部构造
                                node1.setId(id + "");
                                node1.setNodeName(node.get("name").asString());
                                node1.setLabel(next);
                            } else if (LabelType.City.getValue().equals(next)) {
                                //城市
                                node1.setId(id + "");
                                node1.setNodeName(node.get("name").asString());
                                node1.setLabel(next);
                            } else if (LabelType.Type.getValue().equals(next)) {
                                //城市分类
                                node1.setId(id + "");
                                node1.setNodeName(node.get("CityTypeName").asString());
                                node1.setLabel(next);
                            } else if (LabelType.Person.getValue().equals(next)) {
                                //演员
                                node1.setId(id + "");
                                node1.setNodeName(node.get("name").asString());
                                node1.setLabel(next);
                            } else if (LabelType.Movie.getValue().equals(next)) {
                                //电影
                                node1.setId(id + "");
                                node1.setNodeName(node.get("title").asString());
                                node1.setLabel(next);
                            }
                            nodes.add(node1);
                        }
                    } else {
                        //两点直接关系可能有多个
                        List<Object> objects = value.asList();
                        for (int j = 0; j < objects.size(); j++) {
                            Map map = (Map) objects.get(j);
                            Object source1 = map.get("source");
                            long source = (long) source1;

                            Object relationship1 = map.get("relationShip");
                            String relationshipStr = (String) relationship1;
                            relationShips.add(relationshipStr);
                            Object target1 = map.get("target");
                            long target = (long) target1;

                            Relationship relationship = new Relationship();
                            relationship.setText(relationshipStr);
                            relationship.setSource(source + "");
                            relationship.setTarget(target + "");
                            edges.add(relationship);
                        }
                    }
                }
            }
        }
        //节点去重
        nodes = RemoveRepeatDataUtil.removeDuplicateUser(nodes);
        //关系去重
        edges = RemoveRepeatDataUtil.RemoveRepeatRelationShipList(edges);
        //关系名去重
        relationShips = RemoveRepeatDataUtil.removeRepeatString(relationShips);
        resultData.setNodes(nodes);
        resultData.setEdges(edges);
        resultData.setRelationShips(relationShips);
        return resultData;
    }
}
