package xyl.bmsmart.common.common.model.neo4j.node;

import lombok.Data;
//
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
//@NodeEntity
@Data
public class City {

    /**
     * Neo4j会分配的ID
     */
//    @GraphId
//    private Long id;

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
