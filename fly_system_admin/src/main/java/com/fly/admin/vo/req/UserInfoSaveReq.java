package com.fly.admin.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * fly-system 用户信息表
 * </p>
 * @author Fly
 * @since 2022-04-10
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "fly-system 用户信息表-UserInfoSaveReq对象" , description = "fly-system 用户信息表")
public class UserInfoSaveReq{

    @ApiModelProperty(value = "系統主鍵")
    private String uuid;
    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    @NotNull(message = "登录账号不能为空")
    private String loginName;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    @NotNull(message = "登录密码不能为空")
    private String loginPassword;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    @NotNull(message = "用户昵称不能为空")
    private String userName;

    @ApiModelProperty(value = "用户类型")
    @NotNull(message = "用户类型不能为空")
    private Integer userType;

    /**
     * 用户邮箱
     */
    @ApiModelProperty(value = "用户邮箱")
    @NotNull(message = "用户邮箱不能为空")
    private String email;

    /**
     * 用户手机号码
     */
    @ApiModelProperty(value = "用户手机号码")
    @NotNull(message = "用户手机号码不能为空")
    private String phoneNumber;

    /**
     * 用户性别（0男 1女）0男;1女
     */
    @ApiModelProperty(value = "用户性别（0男 1女）")
    @NotNull(message = "用户性别不能为空")
    private Integer sex;

    /**
     * 头像路径
     */
    @ApiModelProperty(value = "头像路径")
    @NotNull(message = "头像路径不能为空")
    private String avatarPath;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}