package xyl.bmsmart.service_neo4j.service_neo4j.service.Impl.neo4j;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.v1.Record;
import org.springframework.stereotype.Service;
import xyl.bmsmart.common.common.model.neo4j.ResultData;
import xyl.bmsmart.common.common.util.CypherResultUtil;
import xyl.bmsmart.service_neo4j.service_neo4j.dao.neo4j.CityAndTypeDao;
import xyl.bmsmart.service_neo4j.service_neo4j.service.neo4j.CityAndTypeService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CityAndTypeServiceImpl implements CityAndTypeService {

    @Resource
    CityAndTypeDao cityAndTypeDao;

    /**
     * 返回所有结果
     *
     * @param label
     * @param condition
     * @param isDirection
     * @param level
     * @return java.lang.String
     * @author XiaYaLing
     * @date 2018/4/26
     */
    @Override
    public ResultData queryAllData(String label, String condition, boolean isDirection, String level) {

        log.info(":::inter " + this.getClass().getName() + ":::方法:::" + Thread.currentThread().getStackTrace()[1].getMethodName() + ":::");

        List<Record> records = cityAndTypeDao.queryAllData(label, condition, isDirection, level);
        ResultData resultData = new ResultData();
        if (records.size() > 0) {
            resultData = CypherResultUtil.dealwithResult(records);
        }
        return resultData;
    }
}
