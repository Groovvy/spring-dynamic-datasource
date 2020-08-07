package com.gitee.groovvy.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.gitee.groovvy.config.prop.Db0Properties;
import com.gitee.groovvy.config.prop.Db1Properties;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.*;

/**
 * @author wanghuaan
 * @date 2020/8/6
 **/
@Configuration
public class DataSourceConfigurer {

	@Autowired private Db0Properties db0Properties;
	
	@Autowired private Db1Properties db1Properties;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfigurer.class);
	
    /**
     * DataSource 自动配置并注册
     *
     * @return data source
     */
    @Bean
    @Primary
    public DataSource dataSource0() {
    	DataSource dataSource = null;
		try {
			dataSource = DruidDataSourceFactory.createDataSource(db0Properties.getProperties());
		} catch (Exception e) {
			LOGGER.error("Create DataSource Error : {}", e);
			throw new RuntimeException();
		}
    	return dataSource;
    }

    /**
     * DataSource 自动配置并注册
     *
     * @return data source
     */
    @Bean
    public DataSource dataSource1() {
    	DataSource dataSource = null;
		try {
			dataSource = DruidDataSourceFactory.createDataSource(db1Properties.getProperties());
		} catch (Exception e) {
			LOGGER.error("Create DataSource Error : {}", e);
			throw new RuntimeException();
		}
    	return dataSource;
    }

    /**
     * 注册动态数据源
     * 
     * @return
     */
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("db0", dataSource0());
        dataSourceMap.put("db1", dataSource1());
        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSource0());// 设置默认数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        return dynamicRoutingDataSource;
    }


    @Bean
//    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 必须将动态数据源添加到 sqlSessionFactoryBean
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        //手动配置mapperlocations
        sqlSessionFactoryBean.setMapperLocations(resolveMapperLocations());
        //配置sql打印
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setLogImpl(StdOutImpl.class);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean;
    }

    /**
     * 自动配置mapperlocations不知道为什么失效，手动配置
     */
    public Resource[] resolveMapperLocations() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<String> mapperLocations = new ArrayList<>();
        mapperLocations.add("classpath:mybatis/mapper/*.xml");
        List<Resource> resources = new ArrayList();
        if (mapperLocations != null) {
            for (String mapperLocation : mapperLocations) {
                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                    // ignore
                }
            }
        }
        return resources.toArray(new Resource[resources.size()]);
    }

    /**
     * 事务管理器
     *
     * @return the platform transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
