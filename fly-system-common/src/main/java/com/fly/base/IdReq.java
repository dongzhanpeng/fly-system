package com.fly.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * IdReq
 * </p>
 *
 * @author Fly
 * @since 2022-05-19
 */
@Data
public class IdReq {

    @ApiModelProperty(value = "系统主键uuid")
    @NotNull(message = "系统主键uuid为空")
    private String uuid;
}
