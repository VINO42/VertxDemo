package io.github.vino42;

import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

/**
 * =====================================================================================
 *
 * @Created :   2022/2/22 1:05
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Email : 38912428@qq.com
 * @Copyright : VINO
 * @Decription :
 * =====================================================================================
 */
public class Server {

    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {

        final Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(MainVerticle.class.getName())
                .onFailure(throwable -> System.exit(-1));

    }


}
