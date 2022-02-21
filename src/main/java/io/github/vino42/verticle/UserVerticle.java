package io.github.vino42.verticle;

import io.github.vino42.domain.dao.UserDao;
import io.github.vino42.handler.ErrorHandler;
import io.github.vino42.handler.UserHandler;
import io.github.vino42.router.UserRouter;
import io.github.vino42.service.UserService;
import io.github.vino42.support.DbUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.mysqlclient.MySQLPool;
import lombok.extern.slf4j.Slf4j;

/**
 * =====================================================================================
 *
 * @Created :   2022/2/21 23:12
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Email : 38912428@qq.com
 * @Copyright : VINO
 * @Decription :
 * =====================================================================================
 */
@Slf4j
public class UserVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserVerticle.class);

    @Override
    public void start(Promise<Void> promise) throws Exception {
        //Pool Connexion
        final MySQLPool dbClient = DbUtils.buildDbClient(vertx);
        final UserDao userDao = new UserDao();
        final UserService userService = new UserService(userDao, vertx, dbClient);
        final UserHandler userHandler = new UserHandler(userService);
        final UserRouter userRouter = new UserRouter(vertx, userHandler);


        final Router router = Router.router(vertx);


        ErrorHandler.buildHandler(router);
//        HealthCheckRouter.setRouter(vertx, router, dbClient);
//        MetricsRouter.setRouter(router);

        userRouter.setRouter(router);

        buildHttpServer(vertx, promise, router);
    }

    private void buildHttpServer(Vertx vertx, Promise<Void> promise, Router router) {
        final int port = 9999;

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(port, http -> {
                    if (http.succeeded()) {
                        promise.complete();
                        LOGGER.info("start vertx success......");
                    } else {
                        promise.fail(http.cause());
                        LOGGER.info("start vertx failed......");
                    }
                });
    }
}
