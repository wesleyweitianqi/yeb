package com.wesley.generator;

        import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
        import com.baomidou.mybatisplus.core.toolkit.StringPool;
        import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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
        import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;


public class CodeGenerator {

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("Please enter" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("Please input right " + tip + "！");
    }

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/yeb-generator/src/main/java");
        gc.setAuthor("wesley");
        gc.setOpen(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);

        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);


        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3308/yeb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia" + "/Shanghai");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("wei123");
        mpg.setDataSource(dsc);


        PackageConfig pc = new PackageConfig();
        pc.setParent("com.wesley")
                .setEntity("pojo")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setController("controller");
        mpg.setPackageInfo(pc);


        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // velocity
        // String templatePath = "/templates/mapper.xml.vm";

        //
        List<FileOutConfig> focList = new ArrayList<>();

        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {

                return projectPath + "/yeb-generator/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper"
                        + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);


        TemplateConfig templateConfig = new TemplateConfig();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);


        StrategyConfig strategy = new StrategyConfig();

        strategy.setNaming(NamingStrategy.underline_to_camel);

        strategy.setColumnNaming(NamingStrategy.no_change);

        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器。 返回前端是json字符串。

        strategy.setInclude(scanner("table, divided by").split(","));
        strategy.setControllerMappingHyphenStyle(true);

        strategy.setTablePrefix("t_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}

