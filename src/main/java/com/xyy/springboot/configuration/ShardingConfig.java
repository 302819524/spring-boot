package com.xyy.springboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:sharding.properties"})
public class ShardingConfig {
}