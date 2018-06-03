package top.wikl.common.myenum;

/**
 * 关系等级
 *
 * @param
 * @author XiaYaLing
 * @date 2018/4/18
 * @return
 */
public enum RelationShipLevel {

    zero("zero", "0"),

    /**
     * 一级关系
     */
    first("first", "1"),

    /**
     * 二级关系
     */
    second("second", "2"),

    /**
     * 三级关系
     */
    third("third", "3"),

    /**
     * 四级关系
     */
    four("four", "4"),

    /**
     * 五级关系
     */
    five("five", "5");

    private String value;

    private String desc;

    RelationShipLevel(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 取得枚举
     *
     * @param value
     * @return
     */
    public static RelationShipLevel getEnumByValue(String value) {
        for (RelationShipLevel enmuType : RelationShipLevel.values()) {
            if (enmuType.getValue().equals(value)) {
                return enmuType;
            }
        }
        return null;
    }
}
