package xyl.bmsmart.service_neo4j.service_neo4j.service.neo4j;


import xyl.bmsmart.common.common.model.neo4j.ResultData;

public interface CityAndTypeService {

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
    ResultData queryAllData(String label, String condition, boolean isDirection, String level);
}
