package com.dfbz.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * spring与mybatis整合配置：
 * 1.数据源配置
 * 2.SqlSessionFactoryBean配置
 * 3.开启扫描mapper接口
 *
 * spring整合日志：
 * 1.导入log4j2\slf4j依赖
 * 2.在resources下放入log4j2.xml
 * 3.在mybtis的configuration设置使用log4j2的日志
 */
@Configuration
@Import(SpringTxConfig.class)
@MapperScan(basePackages = "com.dfbz.mapper")   // 开启扫描mapper接口
@PropertySource(value = "classpath:system.properties", encoding = "utf-8")
public class SpringMybatisConfig {

    // 数据源配置
    @Bean
    public DruidDataSource getDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        InputStream resourceAsStream = SpringMybatisConfig.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        druidDataSource.configFromPropety(properties);  // 自动配置参数
        return druidDataSource;
    }

    @Bean
    public SqlSessionFactoryBean getFactoryBean(DruidDataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);    // 设置数据源
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        tk.mybatis.mapper.session.Configuration configuration = new tk.mybatis.mapper.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);    // 设置支持驼峰命名转换
        configuration.setLogImpl(Log4j2Impl.class); // 使用log4j2日志输出
        sqlSessionFactoryBean.setConfiguration(configuration);
        PageInterceptor pageInterceptor = new PageInterceptor();    // 分页拦截对象
        // 开启分页对象默认设置，解决自动适配方言问题
        pageInterceptor.setProperties(new Properties());
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
        return sqlSessionFactoryBean;
    }

}
