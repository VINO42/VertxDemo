package io.github.vino42.domain.dao;

import io.github.vino42.domain.entity.UserEntity;
import io.vertx.core.Future;
import io.vertx.sqlclient.RowIterator;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.templates.SqlTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * =====================================================================================
 *
 * @Created :   2022/2/22 0:05
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Email : 38912428@qq.com
 * @Copyright : VINO
 * @Decription :
 * =====================================================================================
 */
public class UserDao implements BaseDao<UserEntity> {
    private static final Logger log = LoggerFactory.getLogger(UserDao.class);


    @Override
    public Future<UserEntity> find(SqlConnection con, String id) {
        return SqlTemplate
                .forQuery(con, "select * from user where id = #{id} ")
                .mapTo(UserEntity.class)
                .execute(Collections.singletonMap("id", id))
                .map(rowSet -> {

                    final RowIterator<UserEntity> iterator = rowSet.iterator();

                    if (iterator.hasNext()) {
                        return iterator.next();
                    } else {
                        throw new NoSuchElementException("no such user");
                    }
                })
                .onSuccess(success -> log.info("[get user sucess,user:{}]", success))
                .onFailure(throwable -> log.error("[get userfaled]", throwable));
    }

}
