package top.wikl.service_dgraph.controller;

import com.google.gson.Gson;
import io.dgraph.DgraphClient;
import io.dgraph.DgraphGrpc;
import io.dgraph.DgraphProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wikl.common.model.neo4j.Node;
import top.wikl.common.model.neo4j.Relationship;
import top.wikl.common.model.neo4j.ResultData;
import top.wikl.common.util.RemoveRepeatDataUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    private static final String TEST_HOSTNAME = "localhost";
    private static final int TEST_PORT = 9080;

    @RequestMapping(value = "dgraph")
    public String dgraph() {
        ManagedChannel channel =
                ManagedChannelBuilder.forAddress(TEST_HOSTNAME, TEST_PORT).usePlaintext(true).build();
        DgraphGrpc.DgraphBlockingStub blockingStub = DgraphGrpc.newBlockingStub(channel);
        DgraphClient dgraphClient = new DgraphClient(Collections.singletonList(blockingStub));

        // Initialize
//        dgraphClient.alter(DgraphProto.Operation.newBuilder().setDropAll(true).build());

        // Set schema
      /*  String schema = "  name: string @index(term) .\n" +
                "  release_date: datetime @index(year) .\n" +
                "  revenue: float .\n" +
                "  running_time: int .";
        DgraphProto.Operation op = DgraphProto.Operation.newBuilder().setSchema(schema).build();
        dgraphClient.alter(op);*/

        Gson gson = new Gson(); // For JSON encode/decode

       /* DgraphClient.Transaction txn = dgraphClient.newTransaction();
        try {
            ArrayList<Person> peopleList = new ArrayList<>();
            ArrayList<Movie> modieList = new ArrayList<>();
            // Create data
            Person luke = new Person();
            luke.setId("1");
            luke.setName("Luke Skywalker");

            Person leia = new Person();
            leia.setId("2");
            leia.setName("Princess Leia");

            Person han = new Person();
            han.setId("3");
            han.setName("Han Solo");

            Person lucas = new Person();
            lucas.setId("4");
            lucas.setName("George Lucas");

            Person irvin = new Person("Irvin Kernshner", "5");
            Person richard = new Person("Richard Marquand", "6");

            peopleList.add(luke);
            peopleList.add(leia);
            peopleList.add(han);
            peopleList.add(lucas);
            peopleList.add(irvin);
            peopleList.add(richard);

            //movie
            Movie movie = new Movie();
            movie.setName("Star Wars: Episode IV - A New Hope");
            movie.setRelease_date("1977-05-25");
            movie.setRevenue("775000000");
            movie.setRunning_time("121");
            movie.setStarring(luke);
            movie.setDirector(lucas);

            Movie movie_1 = new Movie();
            movie_1.setName("Star Wars: Episode V - The Empire Strikes Back");
            movie_1.setRelease_date("1980-05-21");
            movie_1.setRevenue("534000000");
            movie_1.setRunning_time("124");
            movie_1.setStarring(luke);
            movie_1.setDirector(irvin);

            modieList.add(movie);
            modieList.add(movie_1);

            for (Person person : peopleList) {
                // Serialize it
                String json = gson.toJson(person);
                // Run mutation
                DgraphProto.Mutation mu =
                        DgraphProto.Mutation.newBuilder().setSetJson(ByteString.copyFromUtf8(json.toString())).build();
                txn.mutate(mu);
            }

            for (Movie movie_ : modieList) {
                // Serialize it
                String json = gson.toJson(movie_);
                // Run mutation
                DgraphProto.Mutation mu =
                        DgraphProto.Mutation.newBuilder().setSetJson(ByteString.copyFromUtf8(json.toString())).build();
                txn.mutate(mu);

            }

            txn.commit();
        } finally {
            txn.discard();
        }*/
        // Query
        String query =
                "" +
                        "query all($a: string){" +
                        "  luke (func:allofterms(name, $a)) {" +
                        "    uid" +
                        "    name" +
                        "    release_date" +
                        "    revenue" +
                        "    running_time" +
                        "    director {" +
                        "     uid" +
                        "     name" +
                        "    }" +
                        "    starring {" +
                        "     uid" +
                        "     name" +
                        "    }" +
                        "  }" +
                        "}";
        Map<String, String> vars = Collections.singletonMap("$a", "Star Wars");
        DgraphProto.Response res = dgraphClient.newTransaction().queryWithVars(query, vars);

        String s = res.getJson().toStringUtf8();
        JSONObject jsonObject = JSONObject.fromObject(s);
        Object luke = jsonObject.get("luke");

        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(luke);


        List<Node> nodes = new ArrayList<>();

        List<Relationship> edges = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            Node node = new Node();
            Object o = jsonArray.get(i);
            JSONObject jsonObject2 = JSONObject.fromObject(o);

            //movie node
            String uid = jsonObject2.getString("uid");
            String name = jsonObject2.getString("name");
            node.setId(uid);
            node.setNodeName(name);
            nodes.add(node);

            Object director = jsonObject2.get("director");
            Object starring = jsonObject2.get("starring");

            net.sf.json.JSONArray jsonArraydirector = net.sf.json.JSONArray.fromObject(director);
            net.sf.json.JSONArray jsonArraystarring = net.sf.json.JSONArray.fromObject(starring);

            //jsonArraydirector
            for (Object o_ : jsonArraydirector) {
                Node node_ = new Node();

                Relationship relationship = new Relationship();

                JSONObject object = JSONObject.fromObject(o_);
                String uid_director = object.getString("uid");
                String name_director = object.getString("name");
                relationship.setSource(uid);
                relationship.setTarget(uid_director);
                relationship.setText(name_director);

                node_.setId(uid_director);
                node_.setNodeName(uid_director);
                nodes.add(node_);
                edges.add(relationship);
            }

            //jsonArraystarring
            for (Object o__ : jsonArraystarring) {
                Node node__ = new Node();
                Relationship relationship = new Relationship();
                JSONObject object_ = JSONObject.fromObject(o__);
                String uid_starring = object_.getString("uid");
                String name_starring = object_.getString("name");
                relationship.setSource(uid);
                relationship.setTarget(uid_starring);
                relationship.setText(name_starring);

                node__.setId(uid_starring);
                node__.setNodeName(name_starring);
                nodes.add(node__);
                edges.add(relationship);
            }

            Movie stu2 = (Movie) JSONObject.toBean(jsonObject2, Movie.class);
            System.out.println("sut2:" + stu2);
        }

        //节点去重
        nodes = RemoveRepeatDataUtil.removeDuplicateUser(nodes);
        //关系去重
        edges = RemoveRepeatDataUtil.RemoveRepeatRelationShipList(edges);

        ResultData resultData = new ResultData();

        resultData.setNodes(nodes);
        resultData.setEdges(edges);
        System.out.println("jsonObject:" + resultData.toString());

        return resultData.toString();
    }
}
