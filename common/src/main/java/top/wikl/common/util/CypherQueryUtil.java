package top.wikl.common.util;

import lombok.extern.slf4j.Slf4j;
import top.wikl.common.myenum.RelationShipLevel;

/**
 * 拼接cypher语句工具类
 *
 * @param
 * @author XiaYaLing
 * @date 2018/4/26
 * @return
 */
@Slf4j
public class CypherQueryUtil {

    /**
     * 根据条件拼接cypher语句
     *
     * @param label       查询的实体对象
     * @param condition   查询条件
     * @param isDirection 关系是否有方向  true : 有方向  false : 无方向
     * @param level       关系等级
     * @return java.lang.String
     * @author XiaYaLing
     * @date 2018/4/18
     */
    public static String dealwithCondition(String label, String condition, boolean isDirection, String level) {

        String cypherSql = "match (label:" + label + ")";
        //根据条件生成具体的执行语句
        //判断关系等级
        if (RelationShipLevel.zero.getDesc().equals(level)) {
            //判断是否有方向
            cypherSql = "match node=(label:" + label + ") return node";

        } else if (RelationShipLevel.first.getDesc().equals(level)) {
            //一级关系
            //判断是否有方向
            if (isDirection) {
                cypherSql = "match node=(label:" + label + ")-[r]->(first) return label,first,collect({relationShip:type(r),source:id(startNode(r)),target:id(endNode(r))}) as edges";
            } else {
                cypherSql = "match p=(label:" + label + ")-[r]-(first)  return label,first,collect({relationShip:type(r),source:id(startNode(r)),target:id(endNode(r))}) as edges";
            }
        } else if (RelationShipLevel.second.getDesc().equals(level)) {
            //二级关系
            //判断是否有方向
            if (isDirection) {
                cypherSql = "match p=(label:" + label + ")-[r]->(first)-[rr]->(second)" +
                        " return collect({relationShip:type(r),source:id(startNode(r)),target:id(endNode(r))}) as edges,collect({relationShip:type(rr),source:id(startNode(rr)),target:id(endNode(rr))}) as edges1,first,second";
            } else {
                cypherSql = "match p=(label:" + label + ")-[r]-(first)-[rr]-(second)" +
                        " return collect({relationShip:type(r),source:id(startNode(r)),target:id(endNode(r))}) as edges,collect({relationShip:type(rr),source:id(startNode(rr)),target:id(endNode(rr))}) as edges1,first,second";
            }
        } else if (RelationShipLevel.third.getDesc().equals(level)) {
            //三级关系
            //判断是否有方向
            if (isDirection) {
                cypherSql = "match p=(label:" + label + ")-[r]->(first)-[rr]->(second)-[rrr]->(third)" +
                        " return collect({relationShip:type(r),source:id(startNode(r)),target:id(endNode(r))}) as edges,collect({relationShip:type(rr),source:id(startNode(rr)),target:id(endNode(rr))}) as edges1,collect({relationShip:type(rrr),source:id(startNode(rrr)),target:id(endNode(rrr))}) as edges2,label,first,second,third";
            } else {
                cypherSql = "match p=(label:" + label + ")-[r]-(first)-[rr]-(second)-[rrr]-(third)" +
                        " return collect({relationShip:type(r),source:id(startNode(r)),target:id(endNode(r))}) as edges,collect({relationShip:type(rr),source:id(startNode(rr)),target:id(endNode(rr))}) as edges1,collect({relationShip:type(rrr),source:id(startNode(rrr)),target:id(endNode(rrr))}) as edges2,label,first,second,third";
            }
        } else if (RelationShipLevel.four.getDesc().equals(level)) {
            //四级关系
            //判断是否有方向
            if (isDirection) {
                cypherSql = "match p=(label:" + label + ")-[r]->(first)-[rr]->(second)-[rrr]->(third)-[rrrr]->(four)" +
                        " return collect({relationShip:type(r),source:id(startNode(r)),target:id(endNode(r))}) as edges,collect({relationShip:type(rr),source:id(startNode(rr)),target:id(endNode(rr))}) as edges1,collect({relationShip:type(rrr),source:id(startNode(rrr)),target:id(endNode(rrr))}) as edges2,collect({relationShip:type(rrrr),source:id(startNode(rrrr)),target:id(endNode(rrrr))}) as edges3,label,first,second,third,four";
            } else {
                cypherSql = "match p=(label:" + label + ")-[r]-(first)-[rr]-(second)-[rrr]-(third)-[rrrr]-(four)" +
                        " return collect({relationShip:type(r),source:id(startNode(r)),target:id(endNode(r))}) as edges,collect({relationShip:type(rr),source:id(startNode(rr)),target:id(endNode(rr))}) as edges1,collect({relationShip:type(rrr),source:id(startNode(rrr)),target:id(endNode(rrr))}) as edges2,collect({relationShip:type(rrrr),source:id(startNode(rrrr)),target:id(endNode(rrrr))}) as edges3,label,first,second,third,four";
            }
        }
        System.out.println("cypher 执行语句: " + cypherSql);
        return cypherSql;
    }
}
