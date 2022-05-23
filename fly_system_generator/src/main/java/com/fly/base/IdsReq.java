package com.fly.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * <p>
 * IdsReq
 * </p>
 *
 * @author Fly
 * @since 2022-05-19
 */
@Data
public class IdsReq {

    @ApiModelProperty(value = "请传入系统主键集合")
    @NotEmpty(message = "请传入系统主键集合")
    private Set<Long> uuids;
}
