package xyl.bmsmart.common.common.model.neo4j;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResultData implements Serializable {

    private List<Node> nodes;

    private List<Relationship> edges;

    private List<String> relationShips;

    @Override
    public String toString() {

        //存在重复的数据在json转化时候的问题（叫循环引用检测）
        return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }
}
