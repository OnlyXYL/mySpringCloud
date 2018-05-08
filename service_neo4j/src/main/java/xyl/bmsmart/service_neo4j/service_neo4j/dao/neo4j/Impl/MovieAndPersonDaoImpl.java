package xyl.bmsmart.service_neo4j.service_neo4j.dao.neo4j.Impl;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.v1.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import xyl.bmsmart.service_neo4j.service_neo4j.dao.neo4j.MovieAndPersonDao;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Slf4j
public class MovieAndPersonDaoImpl implements MovieAndPersonDao {

    @Resource
    Session session;

    /**
     * 根据人名名称 查询深度为1的电影节点节点
     *
     * @param condition
     * @return java.util.List<org.neo4j.driver.v1.Record>
     * @author XiaYaLing
     * @date 2018/5/8
     */
    @Override
    public List<Record> queryDepthOneByName(String condition) {

        log.info(":::inter " + this.getClass().getName() + ":::方法:::" + Thread.currentThread().getStackTrace()[1].getMethodName() + ":::参数:::");

        List<Record> greeting;
        try {
            greeting = session.writeTransaction(new TransactionWork<List<Record>>() {
                @Override
                public List<Record> execute(Transaction tx) {
                    String cypherSql = "";
                    if (!StringUtils.isEmpty(condition)) {
                        cypherSql = "match p=(person:Person{name:\"" + condition + "\"})-[*..1]-(movie)\n" +
                                "return person,movie,extract(n in relationships(p) | (({source:id(startNode(n)),target:id(endNode(n)),relationShip:type(n)}))) as edges";
                    }
                    StatementResult statementResult = tx.run(cypherSql);
                    return statementResult.list();
                }
            });
            //调用方法处理返回结果
            return greeting;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据电影名，查询深度为1的电影和演员新
     *
     * @param title
     * @return java.util.List<org.neo4j.driver.v1.Record>
     * @author XiaYaLing
     * @date 2018/5/8
     * <p>
     * match p=(person)-[*..1]-(movie:Movie{title:"When Harry Met Sally"})
     * return person,movie,extract(n in relationships(p) | (({source:id(startNode(n)),target:id(endNode(n)),relationship:type(n)}))) as edges,nodes(p)
     */
    @Override
    public List<Record> queryDepthOneByTitle(String title) {
        log.info(":::inter " + this.getClass().getName() + ":::方法:::" + Thread.currentThread().getStackTrace()[1].getMethodName() + ":::参数:::");

        List<Record> greeting;
        try {
            greeting = session.writeTransaction(new TransactionWork<List<Record>>() {
                @Override
                public List<Record> execute(Transaction tx) {
                    String cypherSql = "";
                    if (!StringUtils.isEmpty(title)) {
                        cypherSql = "match p=(person)-[*..1]-(movie:Movie{title:\"" + title + "\"})" +
                                "  return person,movie,extract(n in relationships(p) | (({source:id(startNode(n)),target:id(endNode(n)),relationShip:type(n)}))) as edges";
                    }
                    StatementResult statementResult = tx.run(cypherSql);
                    return statementResult.list();
                }
            });
            //调用方法处理返回结果
            return greeting;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据id查询节点深度为1的关系
     *
     * @param id
     * @param label
     * @return java.util.List<org.neo4j.driver.v1.Record>
     * @author XiaYaLing
     * @date 2018/5/8
     * match p=(person:Movie)-[*..1]-(movie)
     * where id(person) = 544
     * return person,id(person),movie,id(movie),extract(n in relationships(p) | (({source:id(startNode(n)),target:id(endNode(n)),relationship:type(n)}))) as edges,nodes(p)
     */
    @Override
    public List<Record> queryDepthOneById(String label, String id) {
        log.info(":::inter " + this.getClass().getName() + ":::方法:::" + Thread.currentThread().getStackTrace()[1].getMethodName() + ":::参数:::");

        List<Record> greeting;
        try {
            greeting = session.writeTransaction(new TransactionWork<List<Record>>() {
                @Override
                public List<Record> execute(Transaction tx) {
                    String cypherSql = "";
                    if (!StringUtils.isEmpty(id)) {
                        cypherSql = "match p=(person:" + label + ")-[*..1]-(movie)" +
                                "    where id(person) = " + id +
                                "    return person,movie,extract(n in relationships(p) | (({source:id(startNode(n)),target:id(endNode(n)),relationShip:type(n)}))) as edges";
                    }
                    StatementResult statementResult = tx.run(cypherSql);
                    return statementResult.list();
                }
            });
            //调用方法处理返回结果
            return greeting;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
