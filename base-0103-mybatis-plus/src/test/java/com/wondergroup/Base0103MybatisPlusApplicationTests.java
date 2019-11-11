package com.wondergroup;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.wondergroup.entity.User;
import com.wondergroup.mapper.UserMapper;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Data
class Base0103MybatisPlusApplicationTests {

    /**
     * 生成代码的主目录,可以在配置文件中配置该属性，默认值是D:/MP-code
     * */
    @Value("${baseDir:D:/MP-code}")
    private String baseDir;

    /** 当前默认的开发者，请修改成自己*/
    @Value("${author:cl}")
    private String author;

    /**
     * 数据库的URL
     */
    @Value("${spring.datasource.druid.url:jdbc:mysql://cl-pc:3306/mybatis-plus?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai}")
    private String dbUrl;

    /**
     * 驱动名称
     */
    @Value("${spring.datasource.druid.driver-class-name:com.mysql.cj.jdbc.Driver}")
    private String driverName;

    /**
     * 数据库用户名
     */
    @Value("${spring.datasource.druid.username:root}")
    private String dbUserName;
    /***
     * 数据库密码
     */
    @Value("${spring.datasource.druid.password:123456}")
    private String dbPassword;

    /**
     * 模块名称*/
    private String moduleName="user";

    /***
     * 包名
     */
    private String modelPackage="com.wondersgroup";


    private String[] tableNames = new String[]{"sso_user","sso_role"};


    @Autowired
    private UserMapper userMapper;


    /**
     * mybatis-plus 自动生成代码
     */
    @Test
    public void autoCreateCode(){
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        //String projectPath = System.getProperty("user.dir");
        System.out.println(baseDir);

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(baseDir + "/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        System.out.println(dbUrl);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dbUrl);
        dsc.setDriverName(driverName);
        dsc.setUsername(dbUserName);
        dsc.setPassword(dbPassword);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(modelPackage);
        mpg.setPackageInfo(pc);


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                /*
                自定义属性注入: 模板配置：abc=${cfg.abc}
                 */
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return baseDir + "/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        /**自定义实体类的父类*/
        //strategy.setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity");
        //strategy.setSuperEntityColumns("id");
        strategy.setEntityLombokModel(true);
        /**设置控制器的父类*/
        //strategy.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
        strategy.setInclude(tableNames);

        strategy.setControllerMappingHyphenStyle(false);
        strategy.setTablePrefix("");
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
        System.out.println("代码自动生成结束");


    }


    @Test
    void contextLoads() {
        User u = insert();
        System.out.println(u);

        User uu = selectById(u.getId());
        System.out.println(uu);

        uu.setEmail("8888");
        User uuu = update(uu);
        System.out.println(uuu);

       // delete(uuu.getId());


    }
    public User selectById(Long id){
        return userMapper.selectById(id);
    }

    public User insert(){
        User u = new User();
        u.setName("chenlin");
        u.setAge(30);
        u.setEmail("chenlin@wondersgroup.com");
        userMapper.insert(u);
        return u;
    }

    public User update(User u){
        userMapper.updateById(u);
        return selectById(u.getId());
    }

    @Transactional
    public void delete(Long id){
        userMapper.deleteById(id);
    }

    @Test
    public void select(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","chenlin");
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.allEq(map);
        List<User> list= userMapper.selectList(wrapper);
        System.out.println(list.get(0));
    }
}
