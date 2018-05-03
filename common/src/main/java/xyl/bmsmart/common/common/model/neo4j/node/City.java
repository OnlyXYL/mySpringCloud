package xyl.bmsmart.common.common.model.neo4j.node;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity
@Data
public class City {

    /**
     * Neo4j会分配的ID
     */
    @GraphId
    private Long id;

    /*************属性*****************/

    /**
     * 城市id
     */
    private String cityId;

    /**
     * 城市分类id
     */
    private String cityTypeId;

    /**
     * 城市名称
     */
    private String name;

}
