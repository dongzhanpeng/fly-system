package com.fly.base;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
    @TableField(updateStrategy = FieldStrategy.NEVER, insertStrategy = FieldStrategy.NEVER)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    @TableField(updateStrategy = FieldStrategy.NEVER, insertStrategy = FieldStrategy.NEVER)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    public String getCreator() {
        return "dongzhanpeng";
    }

    public String getCreatorName() {
        return "董占鹏";
    }

    public String getEditor() {
        return "dongzhanpeng";
    }

    public String getEditorName() {
        return "董占鹏";
    }
}
