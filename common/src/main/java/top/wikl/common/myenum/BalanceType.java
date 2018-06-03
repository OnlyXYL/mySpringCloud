package top.wikl.common.myenum;

/**
 * 负载均衡方式枚举
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/7
 * @return
 */
public enum BalanceType {

    feign("feign", "feign方式"),
    ribbon("ribbon", "ribbon方式");

    private String key;

    private String value;

    BalanceType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 取得枚举
     *
     * @param value
     * @return
     */
    public static BalanceType getEnumByValue(String value) {
        for (BalanceType enmuType : BalanceType.values()) {
            if (enmuType.getValue().equals(value)) {
                return enmuType;
            }
        }
        return null;
    }
}
