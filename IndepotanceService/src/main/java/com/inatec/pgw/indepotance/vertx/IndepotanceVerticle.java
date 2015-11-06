package com.inatec.pgw.indepotance.vertx;

import com.inatec.pgw.indepotance.domain.Transaction;
import com.inatec.pgw.indepotance.storage.KeyProvider;
import com.inatec.pgw.indepotance.storage.Storage;
import com.inatec.pgw.indepotance.storage.impl.CompressedStorage;
import com.inatec.pgw.indepotance.storage.impl.SimpleKeyProvider;
import com.inatec.pgw.indepotance.testing.TransactionGenerator;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import sun.security.provider.certpath.Vertex;

/**
 * Created by Sergey on 05.11.2015.
 */
public class IndepotanceVerticle extends AbstractVerticle {
    TransactionGenerator generator;
    final Storage storage = new CompressedStorage();
    final KeyProvider keyProvider = new SimpleKeyProvider();
    private int bindPort;

    public static void main(String [] args) {
        Vertx.vertx().deployVerticle(new IndepotanceVerticle(Integer.parseInt(args[0])));
    }

    public IndepotanceVerticle(int bindPort) {
        this.bindPort = bindPort;
        generator = new TransactionGenerator();
    }

    @Override
    public void start() throws Exception {
        Router router = Router.router(getVertx());

        router.route().handler(BodyHandler.create());
        router.put("/tnx").handler(this::putTransaction);
        router.post("/tnx").handler(this::getTransaction);

        vertx.createHttpServer().requestHandler(router::accept).listen(bindPort);
    }

    private void getTransaction(RoutingContext routingContext) {
        JsonObject json = routingContext.getBodyAsJson();

        String key = json.getString("key");

        Transaction tnx = storage.get(key);

        HttpServerResponse response = routingContext.response();
        JsonObject jsonObject = new JsonObject().put("transaction", Json.encodePrettily(tnx));
        response.putHeader("content-type", "application/json").end(jsonObject.encodePrettily());
        response.end();
        response.setStatusCode(HttpResponseStatus.OK.code());

    }

    private void putTransaction(RoutingContext routingContext) {
        Transaction tnx = generator.generateTransaction();

        String key = keyProvider.getKey(tnx);

        storage.put(key, tnx);

        HttpServerResponse response = routingContext.response();
        JsonObject jsonObject = new JsonObject().put("key", key);
        response.putHeader("content-type", "application/json").end(jsonObject.encodePrettily());
        response.setStatusCode(HttpResponseStatus.OK.code());
    }
}
