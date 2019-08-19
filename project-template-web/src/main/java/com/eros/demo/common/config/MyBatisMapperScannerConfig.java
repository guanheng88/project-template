package com.eros.demo.common.config;

import java.util.Properties;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("com.eros.demo.mapper");
		
		Properties properties = new Properties();
        properties.setProperty("mappers", Mapper.class.getName());
        properties.setProperty("notEmpty", "false");
        //properties.setProperty("IDENTITY", "SELECT SEQ_MONITOR_OID.NEXTVAL FROM DUAL");
        //properties.setProperty("ORDER", "BEFORE");
        mapperScannerConfigurer.setProperties(properties);
		return mapperScannerConfigurer;
	}
}
