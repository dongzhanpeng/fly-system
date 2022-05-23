package com.fly.generator;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.fly.base.BaseDto;
import com.fly.base.BasePo;
import com.fly.base.BaseQueryVo;
import com.fly.base.CommonMapper;
import com.fly.base.CommonServiceImpl;
import com.fly.base.ICommonService;
import com.fly.base.Result;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * MybatisPlusCodeGeneration
 * </p>
 *
 * @author Fly
 * @since 2022-05-19
 */
@Slf4j
public class MybatisPlusCodeGeneration {

    /**
     * 要逆向生成的表名，以英文逗号隔开，当表较多的情况下尝试以下SQL运行
     * SELECT GROUP_CONCAT(DISTINCT table_name ORDER BY table_name ASC SEPARATOR ',') FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'card' and table_name like 't_demo%';
     */
    private static String[] tables = StrUtil.splitToArray("fly_user_info", ",");
    /**
     * 模块目录
     */
    private static String module="admin";
    private static String controllerModule = "fly-system-admin";
    private static String serviceModule = "fly-system-admin";


    private static String[] commonDir = {"src", "main", "java"};
    private static String root = StrUtil.subBefore(ClassUtil.getPackage(BasePo.class), '.', true);
    private static String controllerPackage = getNormalPackage("controller");
    private static String servicePackage = getNormalPackage("service");
    private static String serviceImplPackage = getSecondPackage("service.impl");
    private static String mapperPackage = getNormalPackage("mapper");
    private static String entityPackage = getNormalPackage("po");
    private static String reqPackage = getNormalPackage("vo.req");
    private static String respPackage = getNormalPackage("vo.resp");
    private static String dtoPackage = getNormalPackage("dto");
    private static String[] xmlPackage = getXmlPackage();

    public void autoGenerator(DataSourceConfig dataSourceConfig) {
        log.info("开始生成代码");
        //生成文件输出根目录
        String oPath = FileUtil.file(System.getProperty("user.dir")).getPath();
        log.info("生成代码路径为{}",oPath);
        AutoGenerator autoGenerator = new MyAutoGenerator();
        autoGenerator.setGlobalConfig(getGlobalConfig(oPath));
        autoGenerator.setDataSource(dataSourceConfig == null ? getDataSourceConfig() : dataSourceConfig);
        autoGenerator.setStrategy(getStrategyConfig());
        autoGenerator.setPackageInfo(getPackageConfig(oPath));
        autoGenerator.setTemplate(getTemplateConfig());
        autoGenerator.setCfg(getInjectionConfig(oPath));
        autoGenerator.execute();
        log.info("生成代码结束");
    }
    /**
     * 自定义配置
     * @return
     */
    private InjectionConfig getInjectionConfig(String oPath){
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> customizeConfigMap = new HashMap<>(10);
//                customizeConfigMap.put("voPackage", voPackage);
                customizeConfigMap.put("dtoPackage", dtoPackage);
                customizeConfigMap.put("reqPackage", reqPackage);
                customizeConfigMap.put("respPackage", respPackage);
                customizeConfigMap.put("basePackage", getBasePackage("com/fly/base"));
                customizeConfigMap.put("utilPackage", getBasePackage("util"));
//                customizeConfigMap.put("superController", CommonController.class.getName());
                customizeConfigMap.put("BaseDto", BaseDto.class.getName());
                customizeConfigMap.put("BaseVo", BaseQueryVo.class.getName());
                customizeConfigMap.put("result", Result.class.getName());
                customizeConfigMap.put("superColumns", this.getConfig().getStrategyConfig().getSuperEntityColumns());
                this.setMap(customizeConfigMap);
            }
        };
        List<FileOutConfig> outConfigs = new ArrayList<>(10);
//        outConfigs.add(new FileOutConfig("templates/vo.java.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return StrUtil.join(File.separator, oPath, serviceModule, commonDir, StrUtil.split(voPackage, ".")) + File.separator + "" + tableInfo.getEntityName() + "Vo" + StringPool.DOT_JAVA;
//            }
//        });
        outConfigs.add(new FileOutConfig("templates/vo_queryReq.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return StrUtil.join(File.separator, oPath, serviceModule, commonDir, StrUtil.split(reqPackage, ".")) + File.separator + "" + tableInfo.getEntityName() + "QueryReq" + StringPool.DOT_JAVA;
            }
        });
        outConfigs.add(new FileOutConfig("templates/vo_saveReq.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return StrUtil.join(File.separator, oPath, serviceModule, commonDir, StrUtil.split(reqPackage, ".")) + File.separator + "" + tableInfo.getEntityName() + "SaveReq" + StringPool.DOT_JAVA;
            }
        });
        outConfigs.add(new FileOutConfig("templates/vo_queryListResp.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return StrUtil.join(File.separator, oPath, serviceModule, commonDir, StrUtil.split(respPackage, ".")) + File.separator + "" + tableInfo.getEntityName() + "ListResp" + StringPool.DOT_JAVA;
            }
        });
        outConfigs.add(new FileOutConfig("templates/vo_detailResp.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return StrUtil.join(File.separator, oPath, serviceModule, commonDir, StrUtil.split(respPackage, ".")) + File.separator + "" + tableInfo.getEntityName() + "DetailResp" + StringPool.DOT_JAVA;
            }
        });

        outConfigs.add(new FileOutConfig("templates/dto.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return StrUtil.join(File.separator, oPath, serviceModule, commonDir, StrUtil.split(dtoPackage, ".")) + File.separator + "" + tableInfo.getEntityName() + "Dto" + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(outConfigs);
        return cfg;
    }
    /**
     * 自定义模板配置
     * @return
     */
    private TemplateConfig getTemplateConfig() {
        TemplateConfig tc = new TemplateConfig();
        tc.setController("templates/controller.java.vm");
        tc.setEntity("templates/entity.java.vm");
        tc.setMapper("templates/mapper.java.vm");
        tc.setService("templates/service.java.vm");
        tc.setServiceImpl("templates/serviceImpl.java.vm");
        tc.setXml("templates/mapper.xml.vm");
        return tc;
    }
    /**
     * 包配置
     * @return
     */
    private PackageConfig getPackageConfig(String oPath) {
        PackageConfig pc = new PackageConfig();
        pc.setParent("");
        pc.setController(controllerPackage);
        pc.setService(servicePackage);
        pc.setServiceImpl(serviceImplPackage);
        pc.setMapper(mapperPackage);
        pc.setEntity(entityPackage);
        //改变存储路径
        Map<String, String> configPathInfo = new HashMap<>(10);
        configPathInfo.put("controller_path", StrUtil.join(File.separator, oPath, controllerModule, commonDir, StrUtil.split(controllerPackage, ".")));
        configPathInfo.put("entity_path", StrUtil.join(File.separator, oPath, serviceModule, commonDir, StrUtil.split(entityPackage, ".")));
        configPathInfo.put("mapper_path", StrUtil.join(File.separator, oPath, serviceModule, commonDir, StrUtil.split(mapperPackage, ".")));
        configPathInfo.put("service_path", StrUtil.join(File.separator, oPath, serviceModule, commonDir, StrUtil.split(servicePackage, ".")));
        configPathInfo.put("service_impl_path", StrUtil.join(File.separator, oPath, serviceModule, commonDir, StrUtil.split(serviceImplPackage, ".")));
        configPathInfo.put("xml_path", StrUtil.join(File.separator, oPath, serviceModule, xmlPackage));
        pc.setPathInfo(configPathInfo);
        return pc;
    }
    /**
     * 策略配置
     * @return
     */
    private StrategyConfig getStrategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        strategy.setInclude(tables);
        strategy.setSuperServiceClass(ICommonService.class.getName());
        strategy.setSuperServiceImplClass(CommonServiceImpl.class.getName());
        strategy.setSuperMapperClass(CommonMapper.class.getName());
        strategy.setRestControllerStyle(true);
//        strategy.setSuperControllerClass(CommonController.class);
        strategy.setSuperEntityColumns(getFields(BasePo.class));
        strategy.setSuperEntityClass(BasePo.class);
        strategy.setEntityLombokModel(true);
        strategy.setEntityTableFieldAnnotationEnable(false);
        strategy.setEntitySerialVersionUID(false);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setRestControllerStyle(true);
        strategy.setVersionFieldName("version");
        //去除表前缀
        strategy.setTablePrefix("fly_");
        //去除字段前缀
        strategy.setFieldPrefix("");
        return strategy;
    }

    private String[] getFields(Class c){
        Field[] fields = ReflectUtil.getFields(c);
        return Arrays.stream(fields).map(z -> z.getName()).toArray(String[]::new);
    }

    /**
     * 全局配置
     * @return
     */
    private GlobalConfig getGlobalConfig(String oPath) {
        //全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(oPath);
        //生成完成后不弹出文件框
        gc.setOpen(false);
        //文件覆盖
        gc.setFileOverride(true);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(false);
        // XML columList
        gc.setBaseColumnList(false);
        // 作者
        gc.setAuthor("Fly");
        gc.setControllerName("%sController");
        gc.setServiceName("I%sService");
        gc.setEntityName("%s");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setSwagger2(true);
        return gc;
    }
    /**
     * 数据源配置
     * @return
     */
    private DataSourceConfig getDataSourceConfig() {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/fly_system_mit?useSSL=false&rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8");
        dsc.setUsername("root");
        dsc.setPassword("root");
        return dsc;
    }

    private static String getNormalPackage(String packageName) {
        return StrUtil.isNotBlank(module) ? StrUtil.join(".", root, module,packageName) : StrUtil.join(".", root, packageName);
    }

    private static String getSecondPackage(String packageName) {
        return StrUtil.join(".", root, module, packageName);
    }

    private static String getBasePackage(String packageName) {
        return StrUtil.join(".", root, packageName);
    }

    private static String[] getXmlPackage() {
        return StrUtil.isNotBlank(module) ? new String[]{"src", "main", "resources", "mapper", module} : new String[]{"src", "main", "resources", "mapper"};
    }

    public static void main(String[] args) {
        MybatisPlusCodeGeneration generation = new MybatisPlusCodeGeneration();
        generation.autoGenerator(generation.getDataSourceConfig());
    }
}