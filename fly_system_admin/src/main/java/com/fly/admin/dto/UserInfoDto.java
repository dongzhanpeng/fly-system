package com.fly.admin.dto;
import lombok.Data;
import lombok.experimental.Accessors;
import com.fly.base.BasePo;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
/**
 * <p>
 * fly-system 用户信息表
 * </p>
 * @author Fly
 * @since 2022-04-10
 */
@Data
@Accessors(chain = true)
public class UserInfoDto extends BasePo {
    /**
     * 登录账号
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String loginPassword;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户类型，0普通用户，1管理员用户
     */
    private Integer userType;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户手机号码
     */
    private String phoneNumber;
    /**
     * 用户性别（0男 1女）0男;1女
     */
    private Integer sex;
    /**
     * 头像路径
     */
    private String avatarPath;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private Date loginDate;
    /**
     * 密码最后更新时间
     */
    private Date passwordUpdateDate;
    /**
     * 登录状态，0登出，1登录，2注销，3锁定
     */
    private Integer accountStatus;
    /**
     * 备注
     */
    private String remark;
    /**
     * 乐观锁version
     */
    private Integer version;

}