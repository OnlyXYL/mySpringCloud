package xyl.bmsmart.service_neo4j.service_neo4j.service.Impl.neo4j;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.v1.Record;
import org.springframework.stereotype.Service;
import xyl.bmsmart.common.common.model.neo4j.ResultData;
import xyl.bmsmart.common.common.util.CypherResultUtil;
import xyl.bmsmart.service_neo4j.service_neo4j.dao.neo4j.MovieAndPersonDao;
import xyl.bmsmart.service_neo4j.service_neo4j.service.neo4j.MovieAndPersonService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 电影相关接口实现类
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/8
 * @return
 */
@Service
@Slf4j
public class MovieAndPersonServiceImpl implements MovieAndPersonService {

    @Resource
    MovieAndPersonDao movieAndPersonDao;

    /**
     * 根据名称 查询深度为1的节点
     *
     * @param name
     * @return xyl.bmsmart.common.common.model.neo4j.ResultData
     * @author XiaYaLing
     * @date 2018/5/8
     */
    @Override
    public ResultData queryDepthOneByName(String name) {
        log.info(":::inter " + this.getClass().getName() + ":::方法:::" + Thread.currentThread().getStackTrace()[1].getMethodName() + ":::");

        List<Record> records = movieAndPersonDao.queryDepthOneByName(name);
        ResultData resultData = new ResultData();
        if (records.size() > 0) {
            resultData = CypherResultUtil.dealwithResult(records);
        }
        return resultData;
    }

    /**
     * 根据电影名，查询深度为1的电影和演员新
     *
     * @param title
     * @return xyl.bmsmart.common.common.model.neo4j.ResultData
     * @author XiaYaLing
     * @date 2018/5/8
     */
    @Override
    public ResultData queryDepthOneByTitle(String title) {
        List<Record> records = movieAndPersonDao.queryDepthOneByTitle(title);
        ResultData resultData = new ResultData();
        if (records.size() > 0) {
            resultData = CypherResultUtil.dealwithResult(records);
        }
        return resultData;
    }

    /**
     * 根据id查询节点深度为1的关系
     *
     * @param label
     * @param id
     * @return xyl.bmsmart.common.common.model.neo4j.ResultData
     * @author XiaYaLing
     * @date 2018/5/8
     */
    @Override
    public ResultData queryDepthOneById(String label, String id) {
        List<Record> records = movieAndPersonDao.queryDepthOneById(label, id);
        ResultData resultData = new ResultData();
        if (records.size() > 0) {
            resultData = CypherResultUtil.dealwithResult(records);
        }
        return resultData;
    }
}
