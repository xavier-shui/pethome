package cn.xavier.quartz.config;

import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

//Quartz配置 - scheduler
@Configuration
public class QuartzConfiguration {
    //主数据源
    @Bean
    @Primary //主数据库，指向pethome
    @ConfigurationProperties(prefix = "spring.datasource")
    DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    //为quartz创建一个自己数据源
    @Bean
    @QuartzDataSource //quartz数据源
    @ConfigurationProperties(prefix = "spring.quartz.properties.org.quartz.datasource")
    DataSource quartzDataSource() {
        return DataSourceBuilder.create().build();
    }
}