package io.github.vino42.handler;

import io.github.vino42.domain.entity.UserEntity;
import io.github.vino42.service.UserService;
import io.github.vino42.support.ResponseUtils;
import io.vertx.core.Future;
import io.vertx.ext.web.RoutingContext;

/**
 * =====================================================================================
 *
 * @Created :   2022/2/21 23:39
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Email : 38912428@qq.com
 * @Copyright : VINO
 * @Decription :
 * =====================================================================================
 */
public class UserHandler {

    private UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Future<UserEntity> getById(RoutingContext rc) {
        final String id = rc.pathParam("id");

        return userService.getByd(id)
                .onSuccess(success -> ResponseUtils.buildOkResponse(rc, success))
                .onFailure(throwable -> ResponseUtils.buildErrorResponse(rc, throwable));
    }
}
