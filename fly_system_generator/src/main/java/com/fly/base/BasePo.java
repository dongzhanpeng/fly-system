package com.fly.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * BasePo
 * </p>
 *
 * @author Fly
 * @since 2022-05-19
 */
@Data
@Accessors(chain = true)
public class BasePo implements Serializable {

    private Long id;

    /**
     * 系统主键
     */
    private String uuid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人姓名
     */
    private String creatorName;

    /**
     * 修改时间
     */
    private Date editTime;

    /**
     * 修改人
     */
    private String editor;

    /**
     * 修改人姓名
     */
    private String editorName;

    /**
     * 0表示正常;1表示逻辑删除
     */
    private Integer deleteFlag;
}
