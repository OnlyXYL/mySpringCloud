package top.wikl.common.model.neo4j;

import java.io.Serializable;

/**
 * 关系对象
 * edges": [
 * {"data": {"relationship": "ACTED_IN", "source": "174", "target": "327"}},
 * {"data": {"relationship": "ACTED_IN", "source": "174", "target": "273"}},
 * ]
 *
 * @param
 * @author XiaYaLing
 * @date 2018/4/13
 * @return
 */
public class Relationship implements Serializable {

    //    private String relationship;
    private String text;

    private String source;

    private String target;

    private String label;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Relationship{" +
                "text='" + text + '\'' +
                ", source='" + source + '\'' +
                ", target='" + target + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

    /**
     * 重写equals,用于比较对象属性是否包含
     *
     * @param obj
     * @return boolean
     * @author XiaYaLing
     * @date 2018/4/17
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Relationship relationship = (Relationship) obj;
        //多重逻辑处理
        if (this.getSource().compareTo(relationship.getSource()) == 0
                && this.getTarget().equals(relationship.getTarget())
                && this.getText().compareTo(relationship.getText()) == 0) {
            return true;
        }
        return false;
    }
}
