package com.fly.admin.vo.req;

import com.fly.base.BaseQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * fly-system 用户信息表
 * </p>
 *
 * @author Fly
 * @since 2022-04-10
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UserInfoQueryReq对象", description = "fly-system 用户信息表")
public class UserInfoQueryReq extends BaseQueryVo {

    /**
     * 用户性别（0男 1女）0男;1女
     */
    @ApiModelProperty(value = "用户性别（0男 1女）0男;1女")
    private Integer sex;

    /**
     * 登录状态，0登出，1登录，2注销，3锁定
     */
    @ApiModelProperty(value = "登录状态，0登出，1登录，2注销，3锁定")
    private Integer accountStatus;

    @ApiModelProperty(value = "用户昵称")
    private String userName;
}