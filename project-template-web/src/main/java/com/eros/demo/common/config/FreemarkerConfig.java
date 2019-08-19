package com.eros.demo.common.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import cn.org.rapid_framework.freemarker.directive.BlockDirective;
import cn.org.rapid_framework.freemarker.directive.ExtendsDirective;
import cn.org.rapid_framework.freemarker.directive.OverrideDirective;

/**
 * 页面继承功能配置
 * 
 * @author guanheng
 *
 */
@Configuration
public class FreemarkerConfig {

	@Autowired
	private freemarker.template.Configuration configuration;

	@PostConstruct
	public void setSharedVariable() {
		configuration.setSharedVariable("block", new BlockDirective());
		configuration.setSharedVariable("override", new OverrideDirective());
		configuration.setSharedVariable("extends", new ExtendsDirective());
	}
}
