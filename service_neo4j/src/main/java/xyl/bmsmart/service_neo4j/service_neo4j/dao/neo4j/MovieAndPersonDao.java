package xyl.bmsmart.service_neo4j.service_neo4j.dao.neo4j;

import org.neo4j.driver.v1.Record;

import java.util.List;

public interface MovieAndPersonDao {

    /**
     * 根据名称 查询深度为1的节点
     *
     * @param condition
     * @return java.util.List<org.neo4j.driver.v1.Record>
     * @author XiaYaLing
     * @date 2018/5/8
     */
    List<Record> queryDepthOneByName(String condition);

    /**
     * 根据电影名，查询深度为1的电影和演员新
     *
     * @param title
     * @return java.util.List<org.neo4j.driver.v1.Record>
     * @author XiaYaLing
     * @date 2018/5/8
     */
    List<Record> queryDepthOneByTitle(String title);

    /**
     * 根据id查询节点深度为1的关系
     *
     * @param id
     * @param label
     * @return java.util.List<org.neo4j.driver.v1.Record>
     * @author XiaYaLing
     * @date 2018/5/8
     */
    List<Record> queryDepthOneById(String label, String id);
}
