package com.fly.admin.service;

import com.fly.admin.dto.UserInfoDto;
import com.fly.admin.po.UserInfo;
import com.fly.admin.vo.req.UserInfoQueryReq;
import com.fly.admin.vo.req.UserInfoSaveReq;
import com.fly.admin.vo.resp.UserInfoDetailResp;
import com.fly.admin.vo.resp.UserInfoListResp;
import com.fly.base.ICommonService;
import com.fly.base.IdReq;
import com.fly.base.IdsReq;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * fly-system 用户信息表 服务类
 * </p>
 *
 * @author Fly
 * @since 2022-04-10
 */
public interface IUserInfoService extends ICommonService<UserInfo> {

    /**
     * dto 查询记录
     *
     * @param dto
     * @return
     */
    List<UserInfo> selectList(UserInfoDto dto);

    /**
     * 分页查询记录
     *
     * @param req
     * @return
     */
    PageInfo<UserInfoListResp> page(UserInfoQueryReq req);

    /**
     * 查询用户详情信息
     *
     * @param req
     * @return
     */
    UserInfoDetailResp detail(IdReq req);

    /**
     * 新增用户
     *
     * @param req
     * @return
     */
    String save(UserInfoSaveReq req) throws Exception;

    /**
     * 用户信息更新
     * @param req
     * @return
     */
    Boolean update(UserInfoSaveReq req);


    /**
     * 批量删除用户
     * @param req
     * @return
     */
    Boolean remove(IdsReq req);
}
