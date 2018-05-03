package xyl.bmsmart.common.common.model.neo4j;

import lombok.Data;

import java.io.Serializable;

@Data
public class Node implements Serializable {

    private String name;
    private String nodeName;
    private String tagline;
    private Integer released;
    private String label;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node person = (Node) o;

        if (!name.equals(person.name)) return false;
        return name.equals(person.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
