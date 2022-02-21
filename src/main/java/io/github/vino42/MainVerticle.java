package io.github.vino42;

import io.github.vino42.verticle.UserVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
@Slf4j
public class MainVerticle extends AbstractVerticle {
    public static final Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);
    private static final String USER_QUEUE = "bus.user.queue";

    // Convenience method so you can run it in your IDE

    @Override
    public void start() throws Exception {

        log.info("MainVerticle starting .................................");
        /**
         EventBus
         */
        EventBus eb = vertx.eventBus();

        /**
         * Consumer can return a MessageConsumer
         * without handler set, and then the handler.
         * This consumer will notify when a message is
         * received.
         */
        MessageConsumer<JsonObject> consumer = eb.consumer(USER_QUEUE);
        consumer.handler(message -> {
            System.out.println("====================================I have received a message: " + message.body());
        });


        deployUserVerticle(vertx)
                .onSuccess(success -> LOGGER.info("deploy verticle ok"))
                .onFailure(throwable -> LOGGER.error("deploy verticle failed"));

//        final io.vertx.mutiny.core.Vertx MVertx = io.vertx.mutiny.core.Vertx.vertx();
//
//        DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("myPort", 8081));
//
//        MVertx.deployVerticle(OtherVerticle::new, options).subscribe().with(  // <2>
//                ok -> {
//                    LOGGER.info("✅ Deployment success");
//                }, err -> LOGGER.error("🔥 Deployment failure", err));

    }

    private Future<String> deployUserVerticle(Vertx vertx) {
        return vertx.deployVerticle(UserVerticle.class.getName());
    }
}
