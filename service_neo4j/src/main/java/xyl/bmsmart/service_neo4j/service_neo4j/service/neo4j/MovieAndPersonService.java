package xyl.bmsmart.service_neo4j.service_neo4j.service.neo4j;

import org.neo4j.driver.v1.Record;
import xyl.bmsmart.common.common.model.neo4j.ResultData;

import java.util.List;

/**
 * 电影相关数据接口
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/8
 * @return
 */
public interface MovieAndPersonService {
    /**
     * 根据名称 查询深度为1的节点
     *
     * @param name
     * @return xyl.bmsmart.common.common.model.neo4j.ResultData
     * @author XiaYaLing
     * @date 2018/5/8
     */
    ResultData queryDepthOneByName(String name);

    /**
     * 根据电影名，查询深度为1的电影和演员新
     *
     * @param title
     * @return xyl.bmsmart.common.common.model.neo4j.ResultData
     * @author XiaYaLing
     * @date 2018/5/8
     */
    ResultData queryDepthOneByTitle(String title);

    /**
     * 根据id查询节点深度为1的关系
     *
     * @param id
     * @param label
     * @return xyl.bmsmart.common.common.model.neo4j.ResultData
     * @author XiaYaLing
     * @date 2018/5/8
     */
    ResultData queryDepthOneById(String label, String id);
}
