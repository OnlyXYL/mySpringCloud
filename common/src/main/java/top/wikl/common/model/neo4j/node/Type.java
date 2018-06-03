package top.wikl.common.model.neo4j.node;

import lombok.Data;

//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
//@NodeEntity
@Data
public class Type {

    /**
     * Neo4j会分配的ID
     */
//    @GraphId
//    private Long id;

    /*************属性*****************/
    /**
     * 城市分类名
     */
    private String CityTypeName;

    /**
     * 城市分类id
     */
    private String cityTypeId;
}
