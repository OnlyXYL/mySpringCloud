package xyl.bmsmart.service_neo4j.service_neo4j.controller;

import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import io.dgraph.DgraphClient;
import io.dgraph.DgraphGrpc;
import io.dgraph.DgraphProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * dgraph demo
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/28
 * @return
 */
@RestController
@RequestMapping(value = "/dgraph")
public class DgraphController {

    @RequestMapping(value = "/testGraph")
    public void testDgraph() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9080).usePlaintext(true).build();
        DgraphGrpc.DgraphBlockingStub blockingStub = DgraphGrpc.newBlockingStub(channel);
        DgraphClient dgraphClient = new DgraphClient(Collections.singletonList(blockingStub));

        String schema = "name: string @index(exact) .";
        DgraphProto.Operation op = DgraphProto.Operation.newBuilder().setSchema(schema).build();
        dgraphClient.alter(op);

        DgraphClient.Transaction txn = dgraphClient.newTransaction();
        try {
            // Create data
            Person p = new Person();
            p.name = "Alice";

// Serialize it
            Gson gson = new Gson();
            String json = gson.toJson(p);
// Run mutation
            DgraphProto.Mutation mu =
                    DgraphProto.Mutation.newBuilder()
                            .setSetJson(ByteString.copyFromUtf8(json.toString()))
                            .build();

            txn.mutate(mu);


            // Query
            String query =
                    "query all($a: string){" +
                            "  all(func: eq(name, $a)) {" +
                            "    name" +
                            "  }" +
                            "}";

            Map<String, String> vars = Collections.singletonMap("$a", "Alice");
            DgraphProto.Response res = dgraphClient.newTransaction().queryWithVars(query, vars);

// Deserialize
            People ppl = gson.fromJson(res.getJson().toStringUtf8(), People.class);

// Print results
            System.out.printf("people found: %d\n", ppl.all.size());
            ppl.all.forEach(person -> System.out.println(person.name));

        } finally {
            txn.discard();
        }


    }
}
