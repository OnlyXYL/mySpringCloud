package top.wikl.common.model.neo4j;

import lombok.Data;

import java.io.Serializable;

@Data
public class Node implements Serializable {

    private String id;
    private String nodeName;
    private String tagline;
    private Integer released;
    private String label;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (!id.equals(node.id)) return false;
        return id.equals(node.id);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
