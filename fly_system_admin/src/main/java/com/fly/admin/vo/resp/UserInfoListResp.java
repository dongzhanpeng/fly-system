package com.fly.admin.vo.resp;

import com.fly.base.BaseRespVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

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
@ApiModel(value = "UserInfoListResp对象", description = "fly-system 用户信息表")
public class UserInfoListResp {
    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    private String loginName;
    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    private String loginPassword;
    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String userName;
    /**
     * 用户类型，0普通用户，1管理员用户
     */
    @ApiModelProperty(value = "用户类型")
    private Integer userType;
    /**
     * 用户邮箱
     */
    @ApiModelProperty(value = "用户邮箱")
    private String email;
    /**
     * 用户手机号码
     */
    @ApiModelProperty(value = "用户手机号码")
    private String phoneNumber;
    /**
     * 用户性别（0男 1女）0男;1女
     */
    @ApiModelProperty(value = "用户性别（0男 1女）0男;1女")
    private Integer sex;
    /**
     * 头像路径
     */
    @ApiModelProperty(value = "头像路径")
    private String avatarPath;
    /**
     * 最后登录IP
     */
    @ApiModelProperty(value = "最后登录IP")
    private String loginIp;
    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    private Date loginDate;
    /**
     * 密码最后更新时间
     */
    @ApiModelProperty(value = "密码最后更新时间")
    private Date passwordUpdateDate;
    /**
     * 登录状态，0登出，1登录，2注销，3锁定
     */
    @ApiModelProperty(value = "登录状态，0登出，1登录，2注销，3锁定")
    private Integer accountStatus;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 乐观锁version
     */
    @ApiModelProperty(value = "乐观锁version")
    private Integer version;
}