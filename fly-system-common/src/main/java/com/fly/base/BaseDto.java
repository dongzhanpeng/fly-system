package com.fly.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * BaseDto
 * </p>
 *
 * @author Fly
 * @since 2022-05-19
 */
@Data
@Accessors(chain = true)
public class BaseDto {

    private Long createUserId;

    private Long updateUserId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
