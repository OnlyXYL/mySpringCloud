package xyl.bmsmart.common.common.myenum;

/**
 * label枚举s
 */
public enum LabelType {
    Movie("Movie", "电影"),
    Person("Person", "演员"),
    Basin("Basin","盆地"),
    First("First","一级构造"),
    Second("Second","二级构造"),
    Third("Third","三级构造"),
    Part("Part","局部构造"),
    City("City","城市"),
    Type("Type","城市分类");

    private String value;
    private String desc;

    LabelType(String value, String desc) {
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
}
