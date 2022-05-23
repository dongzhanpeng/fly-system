package com.fly.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * BaseRespVo
 * </p>
 *
 * @author Fly
 * @since 2022-05-19
 */
@Data
@Accessors(chain = true)
public class BaseRespVo {

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String creator;

    @ApiModelProperty(value = "创建人姓名")
    private String creatorName;

    @ApiModelProperty(value = "修改时间")
    private Date editTime;

    @ApiModelProperty(value = "修改人")
    private String editor;

    @ApiModelProperty(value = "修改人姓名")
    private String editorName;
}
