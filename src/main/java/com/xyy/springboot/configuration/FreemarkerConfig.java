package com.xyy.springboot.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import cn.org.rapid_framework.freemarker.directive.DirectiveUtils;

@Configuration
public class FreemarkerConfig {
    @Autowired
	protected freemarker.template.Configuration configuration;

	@PostConstruct
	public void setSharedVariable() {
		DirectiveUtils.exposeRapidMacros(configuration);
	}
}
