package io.github.vino42.router;

import io.github.vino42.handler.UserHandler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.ext.web.handler.LoggerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * =====================================================================================
 *
 * @Created :   2022/2/21 23:24
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Email : 38912428@qq.com
 * @Copyright : VINO
 * @Decription :
 * =====================================================================================
 */
public class UserRouter {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserRouter.class);
    private final Vertx vertx;
    private final UserHandler userHandler;

    public UserRouter(Vertx vertx, UserHandler userHandler) {
        this.vertx = vertx;
        this.userHandler = userHandler;
    }

    /**
     * Set user API routes
     *
     * @param router Router
     */
    //8888
    public void setRouter(Router router) {
        router.mountSubRouter("/api", buildUserRouter());
    }


    /**
     * Build user API
     * All routes are composed by an error handler, a validation handler and the actual business logic handler
     */
    private Router buildUserRouter() {

        final Router userRouter = Router.router(vertx);

        userRouter.get("/user/:id").handler(LoggerHandler.create(LoggerFormat.DEFAULT)).handler(userHandler::getById);

        return userRouter;

    }
}
