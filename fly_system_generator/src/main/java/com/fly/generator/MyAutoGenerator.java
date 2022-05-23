package com.fly.generator;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * MyAutoGenerator
 * </p>
 *
 * @author Fly
 * @since 2022-05-19
 */
public class MyAutoGenerator extends AutoGenerator {
    @Override
    protected List<TableInfo> getAllTableInfoList(ConfigBuilder config) {
        List<TableInfo> allTableInfoList = super.getAllTableInfoList(config);
        allTableInfoList.forEach(tableInfo -> {
            List<TableField> fields = tableInfo.getFields();
            Set<String> importPackages = tableInfo.getImportPackages();
            importPackages.remove("java.time.LocalDateTime");
            fields.forEach(field -> {
                if (StrUtil.equalsAnyIgnoreCase(field.getPropertyType(),"LocalDateTime","LOCAL_DATE_TIME")) {
                    field.setColumnType(DbColumnType.DATE);
                    importPackages.add("java.util.Date");
                }
                if (StrUtil.equalsIgnoreCase(field.getPropertyType(),"BOOLEAN")) {
                    field.setColumnType(DbColumnType.INTEGER);
                }
            });
        });
        return allTableInfoList;
    }
}
