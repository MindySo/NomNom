package com.ssafy.nomnom.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.nomnom.model.dao")
public class DBConfig {

}
