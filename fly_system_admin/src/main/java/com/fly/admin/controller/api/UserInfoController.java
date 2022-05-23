package com.fly.admin.controller.api;

import com.fly.admin.service.IUserInfoService;
import com.fly.admin.vo.req.UserInfoQueryReq;
import com.fly.admin.vo.req.UserInfoSaveReq;
import com.fly.admin.vo.resp.UserInfoDetailResp;
import com.fly.admin.vo.resp.UserInfoListResp;
import com.fly.base.IdReq;
import com.fly.base.IdsReq;
import com.fly.base.Result;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * fly-system 用户信息表 前端控制器
 * </p>
 *
 * @author Fly
 * @since 2022-04-10
 */
@Slf4j
@RestController
@RequestMapping("/api/fly/user-info")
@Api(value = "用户信息-Api接口", tags = {"用户信息-Api接口"})
public class UserInfoController {

    @Autowired
    private IUserInfoService iUserInfoService;

    @PostMapping("/page")
    @ApiOperation(value = "用户信息表列表分页查询")
    public Result<PageInfo<UserInfoListResp>> page(@RequestBody UserInfoQueryReq req) {
        return Result.success(iUserInfoService.page(req));
    }

    @PostMapping("/detail")
    @ApiOperation(value = "用户信息详情查询")
    public Result<UserInfoDetailResp> detail(@RequestBody @Valid IdReq req) {
        return Result.success(iUserInfoService.detail(req));
    }

    @PostMapping("/save")
    @ApiOperation(value = "用户信息新增")
    public Result<String> save(@RequestBody @Valid UserInfoSaveReq req) throws Exception {
        return Result.success(iUserInfoService.save((req)));
    }

    @PostMapping("/update")
    @ApiOperation(value = "用户信息更新")
    public Result<Boolean> update(@RequestBody @Valid UserInfoSaveReq req) {
        return Result.success(iUserInfoService.update(req));
    }

    @PostMapping("/remove")
    @ApiOperation(value = "用户信息删除")
    public Result<Boolean> remove(@RequestBody @Valid IdsReq req) {
        return Result.success(iUserInfoService.remove(req));
    }
}

