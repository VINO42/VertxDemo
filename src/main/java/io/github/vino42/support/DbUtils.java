package io.github.vino42.support;

import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;

public class DbUtils {

    private static final String HOST_CONFIG = "datasource.host";
    private static final String PORT_CONFIG = "datasource.port";
    private static final String DATABASE_CONFIG = "datasource.database";
    private static final String USERNAME_CONFIG = "datasource.username";
    private static final String PASSWORD_CONFIG = "datasource.password";

    private DbUtils() {

    }

    /**
     * Build DB client that is used to manage a pool of connections
     *
     * @param vertx Vertx context
     * @return MySQL pool
     */
    public static MySQLPool buildDbClient(Vertx vertx) {


        final MySQLConnectOptions connectOptions = new MySQLConnectOptions()
                .setPort(3306)
                .setHost("127.0.0.1")
                .setDatabase("lll")
                .setUser("root")
                .setPassword("nopasswd");

        final PoolOptions poolOptions = new PoolOptions().setMaxSize(5);

        return MySQLPool.pool(vertx, connectOptions, poolOptions);
    }


}
