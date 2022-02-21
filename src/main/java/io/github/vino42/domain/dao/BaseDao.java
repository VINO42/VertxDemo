package io.github.vino42.domain.dao;

import io.vertx.core.Future;
import io.vertx.sqlclient.SqlConnection;

/**
 * =====================================================================================
 *
 * @Created :   2022/2/21 22:59
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Email : 38912428@qq.com
 * @Copyright : VINO
 * @Decription :
 * =====================================================================================
 */
public interface BaseDao<T> {

    Future<T> find(SqlConnection con, String id);

}
