package com.eros.demo.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;

@Configuration
public class MyBatisConfig {

	private static final String CONFIG_XML_PATH = "classpath:mybatis-config.xml";
	private static final String MAPPER_XML_PATH = "classpath*:com/eros/demo/mapper/**/*.xml";

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return new DruidDataSource();
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactory.setConfigLocation(pathMatchingResourcePatternResolver.getResource(CONFIG_XML_PATH));
		sqlSessionFactory.setMapperLocations(pathMatchingResourcePatternResolver.getResources(MAPPER_XML_PATH));
		sqlSessionFactory.setDataSource(dataSource);

		initPlugins(sqlSessionFactory);
		return sqlSessionFactory.getObject();
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	private void initPlugins(SqlSessionFactoryBean sqlSessionFactory) {
		sqlSessionFactory.setPlugins(new Interceptor[] {
		        new PageHelper()
		});
	}
}