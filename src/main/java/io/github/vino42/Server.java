package io.github.vino42;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.Router;

/**
 * =====================================================================================
 *
 * @Created :   2022/2/21 21:51
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Email : 38912428@qq.com
 * @Copyright : VINO
 * @Decription :
 * =====================================================================================
 */
public class Server extends AbstractVerticle {
    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Runner.runExample(Server.class);
    }

    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);

        router.route().handler(routingContext -> {
            routingContext.response().putHeader(HttpHeaders.CONTENT_TYPE, "application/json").end("Hello World!");
        });

        vertx.createHttpServer().requestHandler(router).listen(8080);
    }
}
