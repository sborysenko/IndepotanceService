package com.inatec.pgw.indepotance;

/**
 * Created by Sergey on 17.11.2015.
 */
import com.sun.management.OperatingSystemMXBean;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import sun.management.ManagementFactoryHelper;

/**
 * Created by Sergey on 16.11.2015.
 */
public class MemoryInfoVerticle extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        router.get("/info").handler(this::checkMemory);

        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(8888, result -> {
                    if (result.succeeded()) {
                        startFuture.complete();
                    } else {
                        startFuture.fail(result.cause());
                    }
                });

    }

    private void checkMemory(RoutingContext routingContext) {
        OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactoryHelper.getOperatingSystemMXBean();
        long max = bean.getTotalPhysicalMemorySize();
        long free = bean.getFreePhysicalMemorySize();

        routingContext.response()
                .putHeader("Content-Type", "text/plain")
                .setStatusCode(HttpResponseStatus.OK.code())
                .end(String.valueOf(max - free));
    }
}
