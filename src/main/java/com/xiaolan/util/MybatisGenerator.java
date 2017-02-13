package com.xiaolan.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by itsxu on 2016/9/1.
 */
public class MybatisGenerator {
	public void generetor() throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
//		File configFile = new File("/home/fallen/IdeaProjects/XiaoLanMall/src/main/resources/mybatis/GeneratorConfig.xml");
		File configFile=FileFinder.getFileByName("GeneratorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
	public static void main(String[] args) throws Exception {
		new MybatisGenerator().generetor();
	}

}
