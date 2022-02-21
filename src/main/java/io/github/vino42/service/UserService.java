package io.github.vino42.service;

import io.github.vino42.domain.dao.UserDao;
import io.github.vino42.domain.entity.UserEntity;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLPool;
import lombok.extern.slf4j.Slf4j;

/**
 * =====================================================================================
 *
 * @Created :   2022/2/21 23:59
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Email : 38912428@qq.com
 * @Copyright : VINO
 * @Decription :
 * =====================================================================================
 */
@Slf4j
public class UserService {
    private static final String BOOKS_QUEUE = "bus.user.queue";
    private final UserDao userDao;
    private final Vertx vertx;
    private final MySQLPool dbClient;

    public UserService(UserDao userDao, Vertx vertx, MySQLPool dbClient) {
        this.userDao = userDao;
        this.vertx = vertx;
        this.dbClient = dbClient;
    }

    public Future<UserEntity> getByd(String id) {
        return dbClient.withTransaction(sqlConnection -> userDao.find(sqlConnection, id)
                ).onSuccess(success -> log.info("ok, user:{}", success))
                .onFailure(throwable -> log.error("failed:{}", throwable));

    }
}
