package com.fly.base;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * BaseQueryVo
 * </p>
 *
 * @author Fly
 * @since 2022-05-19
 */
@Data
@Accessors(chain = true)
public class BaseQueryVo {

    @ApiModelProperty(value = "每页显示条数，默认 10")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "当前页，默认 1")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "排序集合")
    private List<OrderItem> orders;
}
