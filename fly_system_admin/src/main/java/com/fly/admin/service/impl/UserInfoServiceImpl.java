package com.fly.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.fly.admin.dto.UserInfoDto;
import com.fly.admin.enums.AccountStatusEnum;
import com.fly.admin.mapper.UserInfoMapper;
import com.fly.admin.po.UserInfo;
import com.fly.admin.service.IUserInfoService;
import com.fly.admin.vo.req.UserInfoQueryReq;
import com.fly.admin.vo.req.UserInfoSaveReq;
import com.fly.admin.vo.resp.UserInfoDetailResp;
import com.fly.admin.vo.resp.UserInfoListResp;
import com.fly.base.CommonServiceImpl;
import com.fly.base.IdReq;
import com.fly.base.IdsReq;
import com.fly.enums.WhetherEnmu;
import com.fly.exception.BizException;
import com.fly.util.IpUtil;
import com.fly.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * fly-system 用户信息 服务实现类
 * </p>
 *
 * @author Fly
 * @since 2022-04-10
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends CommonServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Override
    public List<UserInfo> selectList(UserInfoDto dto) {
        return null;
    }

    @Override
    public PageInfo<UserInfoListResp> page(UserInfoQueryReq req) {

        log.info("fly page req:{}", JSON.toJSONString(req));
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        PageInfo pageInfo = new PageInfo(this.lambdaQuery()
                .eq(UserInfo::getDeleteFlag, WhetherEnmu.NO.getKey())
                .eq(Objects.nonNull(req.getSex()), UserInfo::getSex, req.getSex())
                .eq(Objects.nonNull(req.getAccountStatus()), UserInfo::getAccountStatus, req.getAccountStatus())
                .like(StringUtils.isNotEmpty(req.getUserName()), UserInfo::getUserName, req.getUserName())
                .list());
        pageInfo.setList(BeanUtil.copyToList(pageInfo.getList(), UserInfoListResp.class));
        log.info("fly page resp:{}", JSON.toJSONString(pageInfo));
        return pageInfo;
    }

    @Override
    public UserInfoDetailResp detail(IdReq req) {

        log.info("fly detail req:{}", JSON.toJSONString(req));
        UserInfoDetailResp userInfoDetailResp = BeanUtil.copyProperties(this.lambdaQuery()
                .eq(UserInfo::getUuid, req.getUuid())
                .eq(UserInfo::getDeleteFlag, WhetherEnmu.NO.getKey())
                .one(), UserInfoDetailResp.class);
        log.info("fly detail resp:{}", JSON.toJSONString(userInfoDetailResp));
        return userInfoDetailResp;
    }

    @Override
    public String save(UserInfoSaveReq req) throws Exception {

        log.info("fly save req:{}", JSON.toJSONString(req));
        UserInfo userInfo = BeanUtil.copyProperties(req, UserInfo.class);
        userInfo.setUuid(UuidUtil.getUuid());
        userInfo.setLoginIp(IpUtil.getLocalIpAddress());
        userInfo.setPasswordUpdateDate(new Date());
        userInfo.setAccountStatus(AccountStatusEnum.LOGOUT.getKey());
        this.save(userInfo);
        log.info("fly save resp:{}", JSON.toJSONString(userInfo));
        return userInfo.getUuid();
    }

    @Override
    public Boolean update(UserInfoSaveReq req) {

        log.info("fly update req:{}", JSON.toJSONString(req));
        if (StringUtils.isEmpty(req.getUuid())) {
            throw new BizException("uuid is null");
        }
        if (StringUtils.isNotEmpty(req.getLoginName())) {
            throw new BizException("loginName is not edit");
        }
        UserInfo userInfo = this.lambdaQuery()
                .eq(UserInfo::getUuid, req.getUuid())
                .eq(UserInfo::getDeleteFlag, WhetherEnmu.NO.getKey())
                .one();
        if (Objects.isNull(userInfo)) {
            throw new BizException("The user is not exist");
        }
        userInfo = BeanUtil.copyProperties(req, UserInfo.class);
        this.lambdaUpdate()
                .eq(UserInfo::getUuid, req.getUuid())
                .eq(UserInfo::getDeleteFlag, WhetherEnmu.NO.getKey())
                .update(userInfo);
        log.info("fly update resp:{}", JSON.toJSONString(userInfo));
        return Boolean.TRUE;
    }

    @Override
    public Boolean remove(IdsReq req) {

        log.info("fly remove req:{}", JSON.toJSONString(req));
        List<UserInfo> userInfoList = this.lambdaQuery()
                .eq(UserInfo::getDeleteFlag, WhetherEnmu.NO.getKey())
                .in(CollectionUtils.isNotEmpty(req.getUuids()), UserInfo::getUuid, req.getUuids())
                .list();
        if (CollectionUtils.isEmpty(userInfoList)) {
            throw new BizException("this user is not exist");
        }
        //只能删除普通用户
        List<String> ordinaryUserUuid = userInfoList.stream().filter(entity -> WhetherEnmu.NO.getKey().compareTo(entity.getUserType()) == 0).map(UserInfo::getUuid).collect(Collectors.toList());
        this.lambdaUpdate()
                .in(CollectionUtils.isNotEmpty(ordinaryUserUuid), UserInfo::getUuid, ordinaryUserUuid)
                .eq(UserInfo::getDeleteFlag, WhetherEnmu.NO.getKey())
                .set(UserInfo::getDeleteFlag, WhetherEnmu.YES.getKey())
                .update();
        return Boolean.TRUE;
    }
}
